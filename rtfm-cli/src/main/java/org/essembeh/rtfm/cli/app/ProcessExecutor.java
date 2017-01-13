package org.essembeh.rtfm.cli.app;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProcessExecutor {

	private final boolean dryrun;
	private final Logger logger;

	public ProcessExecutor(Logger logger, boolean dryrun) {
		this.logger = logger;
		this.dryrun = dryrun;
	}

	public int run(List<String> command) throws IOException, InterruptedException {
		int rc = 0;
		if (dryrun) {
			logger.debug("  Dry-run: %s", command);
		} else {
			ProcessBuilder processBuilder = new ProcessBuilder(command);
			Process p = null;
			try {
				logger.debug("  Execute: %s", command);
				p = processBuilder.start();
				rc = p.waitFor();
			} finally {
				Optional.ofNullable(p).ifPresent(Process::destroy);
			}
		}
		return rc;
	}
}
