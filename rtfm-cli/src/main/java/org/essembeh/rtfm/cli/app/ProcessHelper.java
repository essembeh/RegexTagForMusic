package org.essembeh.rtfm.cli.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

public class ProcessHelper {

	public static String escapeCommand(List<String> command, char escape) {
		return command.stream().map(s -> {
			if (s.contains(" ")) {
				return String.format("%c%s%c", escape, s, escape);
			}
			return s;
		}).collect(Collectors.joining(" "));
	}

	public class Status {
		private final Optional<Integer> returnCode;
		private final List<String> stdout;

		private Status(Integer returnCode, List<String> stdout) {
			this.returnCode = Optional.ofNullable(returnCode);
			this.stdout = Collections.unmodifiableList(stdout);
		}

		public boolean isDryRun() {
			return !returnCode.isPresent();
		}

		public int getReturnCode() {
			return returnCode.orElse(0);
		}

		public List<String> getStdout() {
			return stdout;
		}

		public boolean isOk() {
			return getReturnCode() == 0;
		}
	}

	private final String commandId;
	private final List<String> rawCommand;
	private final List<String> resolvedCommand;

	public ProcessHelper(String commandId, List<String> rawCommand, List<String> resolvedCommand) {
		super();
		this.commandId = commandId;
		this.rawCommand = Collections.unmodifiableList(rawCommand);
		this.resolvedCommand = Collections.unmodifiableList(resolvedCommand);
	}

	public Status execute(boolean dryrun) throws InterruptedException, IOException {
		if (dryrun) {
			return new Status(null, new ArrayList<>());
		}
		ProcessBuilder processBuilder = new ProcessBuilder(resolvedCommand);
		processBuilder.redirectErrorStream(true);
		Process process = processBuilder.start();
		int rc = process.waitFor();
		return new Status(rc, IOUtils.readLines(process.getInputStream()));
	}

	public String getCommandId() {
		return commandId;
	}

	public List<String> getRawCommand() {
		return rawCommand;
	}

	public List<String> getResolvedCommand() {
		return resolvedCommand;
	}
}
