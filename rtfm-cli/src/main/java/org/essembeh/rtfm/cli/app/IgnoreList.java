package org.essembeh.rtfm.cli.app;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import org.essembeh.rtfm.cli.callback.DefaultCallback;
import org.essembeh.rtfm.cli.callback.ICallback;
import org.essembeh.rtfm.cli.utils.ResourceUtils;

public class IgnoreList {

	private final Set<String> ignoredFiles = new TreeSet<>();

	public void load(Path input) throws IOException {
		if (Files.isRegularFile(input)) {
			ignoredFiles.addAll(Files.readAllLines(input));
		}
	}

	public void save(Path output) throws IOException {
		if (!ignoredFiles.isEmpty()) {
			Files.write(output, ignoredFiles, Charset.defaultCharset());
		}
	}

	public boolean shouldProcess(Path in) {
		return !ignoredFiles.contains(ResourceUtils.getFullPath(in));
	}

	public void ignore(Path... in) {
		Stream.of(in).map(ResourceUtils::getFullPath).forEach(ignoredFiles::add);
	}

	public ICallback watch(Path in) {
		AtomicBoolean hasError = new AtomicBoolean(false);
		return new DefaultCallback() {
			@Override
			public void commandEnds(String commandId, ProcessStatus result) {
				hasError.set(result.getReturnCode() != 0);
			}

			@Override
			public void workflowException(Exception e) {
				hasError.set(true);
			}

			@Override
			public void done() {
				if (!hasError.get()) {
					ignore(in);
				}
			}
		};
	}
}
