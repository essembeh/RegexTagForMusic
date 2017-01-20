package org.essembeh.rtfm.cli.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.cli.app.ProcessStatus;
import org.essembeh.rtfm.cli.app.callback.ICallback;

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
	public void fileSkipped(String fullpath, String workflowId, Date lastExecution) {
		print(Level.INFO, 0, "Skip workflow %s, last execution %s", workflowId, SimpleDateFormat.getDateTimeInstance().format(lastExecution));
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
	public void workflowBegins(String id, List<String> commands) {
		print(Level.INFO, 1, "Workflow starts: %s %s", id, commands);
	}

	@Override
	public void workflowEnds(String id, boolean complete) {
		if (complete) {
			print(Level.INFO, 1, "Workflow ends: %s", id);
		} else {
			print(Level.ERROR, 1, "Workflow ends with error: %s", id);
		}

	}

	@Override
	public void workflowException(String id, Exception e) {
		print(Level.ERROR, 1, "Exception in workflow %s: %s", id, e.getMessage());
		e.printStackTrace(System.out);
	}

	@Override
	public void done(String fullpath) {
	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand, List<String> resolvedCommand) {
		print(Level.INFO, 1, "Command %s: %s", commandId, rawCommand);
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
