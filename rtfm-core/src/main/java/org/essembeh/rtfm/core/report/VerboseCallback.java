package org.essembeh.rtfm.core.report;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.core.app.ProcessHelper;
import org.essembeh.rtfm.core.app.ProcessHelper.Status;
import org.essembeh.rtfm.core.app.callback.DefaultCallback;

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
		String formatFull = "[" + level + "] " + StringUtils.repeat(INDENT, indent) + format;
		consoleWriter.printMessage(formatFull, args);
	}

	@Override
	public void fileSkipped(String workflowId, Date lastExecution) {
		print(Level.INFO, 0, "Skip workflow {0}, last execution {1}", workflowId, SimpleDateFormat.getDateTimeInstance().format(lastExecution));
	}

	@Override
	public void unknownType() {
		print(Level.INFO, 0, "Unknown: " + getFullpath());
	}

	@Override
	public void workflowStart(String workflowId, List<String> commands) {
		print(Level.INFO, 0, "Found: [{0}] {1}", workflowId, getFullpath());
		print(Level.INFO, 1, "Workflow {0}: {1}", workflowId, commands);
	}

	@Override
	public void workflowDone(String workflowId, boolean complete) {
		if (complete) {
			print(Level.INFO, 1, "Workflow ends: " + workflowId);
		} else {
			print(Level.ERROR, 1, "Workflow ends with error: " + workflowId);
		}

	}

	@Override
	public void workflowException(String id, Exception e) {
		print(Level.ERROR, 1, "Exception in workflow {0}: {1}", id, e.getMessage());
		e.printStackTrace(System.out);
	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand, List<String> resolvedCommand) {
		print(Level.INFO, 1, "Command {0}: {1}", commandId, rawCommand);
		print(Level.INFO, 2, "Execute: " + ProcessHelper.escapeCommand(resolvedCommand, '"'));
	}

	@Override
	public void commandEnds(String commandId, Status status) {
		if (status.isDryRun()) {
			print(Level.INFO, 2, "Dry-run mode");
		} else if (status.getReturnCode() != 0) {
			print(Level.ERROR, 2, "Return: " + status.getReturnCode());
			status.getStdout().forEach(l -> print(Level.DEBUG, 2, l));
		} else {
			print(Level.INFO, 2, "Return: " + status.getReturnCode());
		}
	}
}
