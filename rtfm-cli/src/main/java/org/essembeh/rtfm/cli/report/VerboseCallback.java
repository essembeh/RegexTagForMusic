package org.essembeh.rtfm.cli.report;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.cli.app.ProcessHelper;
import org.essembeh.rtfm.cli.app.ProcessHelper.Status;
import org.essembeh.rtfm.cli.app.callback.DefaultCallback;

public class VerboseCallback extends DefaultCallback {

	private static final String INDENT = "   ";

	enum Level {
		INFO,
		DEBUG,
		ERROR
	}

	private final ConsoleWriter consoleWriter;

	public VerboseCallback(Path fullpath, ConsoleWriter consoleWriter) {
		super(fullpath);
		this.consoleWriter = consoleWriter;
	}

	private void print(Level level, int indent, String format, Object... args) {
		consoleWriter.printLine("[" + level + "] " + StringUtils.repeat(INDENT, indent) + String.format(format, args));
	}

	@Override
	public void fileSkipped(String workflowId, Date lastExecution) {
		print(Level.INFO, 0, "Skip workflow %s, last execution %s", workflowId, SimpleDateFormat.getDateTimeInstance().format(lastExecution));
	}

	@Override
	public void unknownType() {
		print(Level.INFO, 0, "Unknown: %s", getFullpath());
	}

	@Override
	public void workflowStart(String workflowId, List<String> commands) {
		print(Level.INFO, 0, "Found: [%s] %s", workflowId, getFullpath());
		print(Level.INFO, 1, "Workflow %s: %s", workflowId, commands);
	}

	@Override
	public void workflowDone(String workflowId, boolean complete) {
		if (complete) {
			print(Level.INFO, 1, "Workflow ends: %s", workflowId);
		} else {
			print(Level.ERROR, 1, "Workflow ends with error: %s", workflowId);
		}

	}

	@Override
	public void workflowException(String id, Exception e) {
		print(Level.ERROR, 1, "Exception in workflow %s: %s", id, e.getMessage());
		e.printStackTrace(System.out);
	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand, List<String> resolvedCommand) {
		print(Level.INFO, 1, "Command %s: %s", commandId, rawCommand);
		print(Level.INFO, 2, "Execute: %s", ProcessHelper.escapeCommand(resolvedCommand, '"'));
	}

	@Override
	public void commandEnds(String commandId, Status status) {
		if (status.isDryRun()) {
			print(Level.INFO, 2, "Dry-run mode");
		} else if (status.getReturnCode() != 0) {
			print(Level.ERROR, 2, "Return: %d", status.getReturnCode());
			status.getStdout().forEach(l -> print(Level.DEBUG, 2, l));
		} else {
			print(Level.INFO, 2, "Return: %d", status.getReturnCode());
		}
	}
}
