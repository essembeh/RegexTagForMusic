package org.essembeh.rtfm.cli.app;

public class Logger {

	public static final int DEBUG = 1 << 1;
	public static final int INFO = 1 << 2;
	public static final int ERROR = 1 << 3;

	private final int mask;

	public Logger(int mask) {
		this.mask = mask;
	}

	public void print(int type, String format, Object... args) {
		if ((mask & type) > 0) {
			System.out.println(String.format(format, args));
		}
	}

	public void info(String format, Object... args) {
		print(INFO, format, args);
	}

	public void error(String format, Object... args) {
		print(ERROR, format, args);
	}

	public void debug(String format, Object... args) {
		print(DEBUG, format, args);
	}

	public void dump(Exception e) {
		if ((mask & DEBUG) > 0) {
			e.printStackTrace(System.err);
		}
	}

}
