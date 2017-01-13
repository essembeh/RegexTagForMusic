package org.essembeh.rtfm.cli;

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
}
