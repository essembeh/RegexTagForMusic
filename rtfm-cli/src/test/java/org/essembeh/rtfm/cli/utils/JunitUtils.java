package org.essembeh.rtfm.cli.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;

public class JunitUtils {
	@FunctionalInterface
	public interface RunnableWithException {
		void run() throws Exception;
	}

	public static void assertException(RunnableWithException runnable, Class<? extends Exception> expectedException) {
		try {
			runnable.run();
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals(expectedException, e.getClass());
		}
	}

	public static Path copyConfigTree(Path in) throws IOException {
		Path out = Files.createTempDirectory("rtfm-test-" + in.getFileName().toString());
		FileUtils.copyDirectory(in.toFile(), out.toFile());
		return out;
	}
}
