package org.essembeh.rtfm.core.report;

import java.nio.file.Path;

import org.essembeh.rtfm.core.app.callback.ICallback;

public class ConsoleReport {

	private final boolean verbose;
	private final int threads;

	public ConsoleReport(boolean verbose, int threads) {
		this.verbose = verbose;
		this.threads = threads;
	}

	public ICallback createCallback(Path in) {
		ConsoleWriter consoleWriter = ConsoleWriter.create(in, threads > 1);
		return verbose ? new VerboseCallback(in, consoleWriter) : new QuietCallback(in, consoleWriter);
	}
}
