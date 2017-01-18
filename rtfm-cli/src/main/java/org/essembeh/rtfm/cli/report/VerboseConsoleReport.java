package org.essembeh.rtfm.cli.report;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.cli.app.ICallback;
import org.essembeh.rtfm.cli.app.ProcessStatus;

public class VerboseConsoleReport implements ICallback {

	private static final String INDENT = "   ";

	enum Level {
		INFO,
		DEBUG,
		ERROR
	}

	private static String toString(List<String> command) {
		return command.stream().map(s -> {
			if (s.contains(" ")) {
				return String.format("'%s'", s);
			}
			return s;
		}).collect(Collectors.joining(" "));
	}

	private void print(Level level, int indent, String format, Object... args) {
		System.out.println("[" + level + "] " + StringUtils.repeat(INDENT, indent) + String.format(format, args));
	}

	@Override
	public void unknownType(String fullpath) {
		print(Level.INFO, 0, "Unknown: %s", fullpath);
	}

	@Override
	public void fileHandled(String fullpath, String filehandlerId) {
		print(Level.INFO, 0, "Found: [%s] %s", filehandlerId, fullpath);
	}

	@Override
	public void workflowBegins(List<String> workflow) {
		print(Level.INFO, 1, "Workflow: %s", workflow);
	}

	@Override
	public void workflowException(Exception e) {
		print(Level.ERROR, 2, "Exception: " + e.getMessage());
		e.printStackTrace(System.out);
	}

	@Override
	public void done() {
	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand) {
		print(Level.INFO, 1, "Command: %s %s", commandId, rawCommand);
	}

	@Override
	public void commandResolved(String commandId, List<String> resolvedCommand) {
		print(Level.INFO, 2, "Execute: %s", toString(resolvedCommand));

	}

	@Override
	public void commandEnds(String commandId, ProcessStatus status) {
		if (status.getReturnCode() != 0) {
			print(Level.ERROR, 2, "Return: %d", status.getReturnCode());
			status.getStdout().forEach(l -> print(Level.DEBUG, 2, l));
		} else {
			print(Level.INFO, 2, "Return: %d", status.getReturnCode());
		}
	}
}
