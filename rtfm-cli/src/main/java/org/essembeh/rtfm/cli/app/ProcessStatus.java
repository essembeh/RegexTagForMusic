package org.essembeh.rtfm.cli.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.IOUtils;

public class ProcessStatus {

	public static ProcessStatus execute(List<String> command) throws IOException, InterruptedException {
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		int rc = process.waitFor();
		return new ProcessStatus(command, rc, IOUtils.readLines(process.getInputStream()));

	}

	public static ProcessStatus dryRun(List<String> command) {
		return new ProcessStatus(command, null, new ArrayList<>());
	}

	private final List<String> command;
	private final Optional<Integer> returnCode;
	private final List<String> stdout;

	private ProcessStatus(List<String> command, Integer returnCode, List<String> stdout) {
		this.command = new ArrayList<>(command);
		this.returnCode = Optional.ofNullable(returnCode);
		this.stdout = new ArrayList<>(stdout);
	}

	public boolean isDryRun() {
		return !returnCode.isPresent();
	}

	public int getReturnCode() {
		return returnCode.orElse(0);
	}

	public List<String> getStdout() {
		return Collections.unmodifiableList(stdout);
	}

	public List<String> getCommand() {
		return Collections.unmodifiableList(command);
	}
}