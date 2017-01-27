package org.essembeh.rtfm.cli.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.essembeh.rtfm.cli.app.ProcessHelper;
import org.essembeh.rtfm.cli.app.callback.DefaultCallback;
import org.essembeh.rtfm.cli.app.callback.ICallback;

public class ScriptWriter implements Closeable {

	private static final List<String> HEADER = Arrays.asList("#!/bin/sh", "set -e", "");

	private final Writer writer;

	public ScriptWriter(Optional<Path> output) throws IOException {
		this.writer = output.isPresent() ? Files.newBufferedWriter(output.get()) : null;
		if (writer != null) {
			HEADER.forEach(this::print);
		}
	}

	private void print(String line) {
		try {
			writer.append(line + "\n");
			writer.flush();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public ICallback createCallback(Path in) {
		if (writer != null) {
			return new DefaultCallback(in) {
				@Override
				public void workflowStart(String workflowId, List<String> commands) {
					print("# " + workflowId + ": " + getFullpath());
				}

				@Override
				public void commandBegins(String commandId, List<String> rawCommand, List<String> resolvedCommand) {
					print("# " + commandId);
					print(ProcessHelper.escapeCommand(resolvedCommand, '"'));
				}

				@Override
				public void workflowDone(String workflowId, boolean complete) {
					print("");
				}
			};
		}
		return null;
	}

	@Override
	public void close() throws IOException {
		IOUtils.closeQuietly(writer);
	}
}
