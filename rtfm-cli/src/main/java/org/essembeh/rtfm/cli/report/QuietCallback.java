package org.essembeh.rtfm.cli.report;

import java.nio.file.Path;
import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessHelper.Status;
import org.essembeh.rtfm.cli.app.callback.DefaultCallback;

public class QuietCallback extends DefaultCallback {

	private final ConsoleWriter consoleWriter;

	public QuietCallback(Path path, ConsoleWriter consoleWriter) {
		super(path);
		this.consoleWriter = consoleWriter;
	}

	@Override
	public void unknownType() {
		consoleWriter.printLine("UNKNOWN " + getFullpath());
	}

	@Override
	public void workflowStart(String workflowId, List<String> commands) {
		consoleWriter.printLine(String.format("[%s] %s", workflowId, getFullpath()));
	}

	@Override
	public void workflowException(String workflowId, Exception e) {
		consoleWriter.printLine(String.format("\tEXCEPTION in workflow %s: %s", workflowId, e.getMessage()));
	}

	@Override
	public void commandEnds(String commandId, Status status) {
		if (status.getReturnCode() != 0) {
			consoleWriter.printLine("\tERROR " + commandId + " exited with " + status.getReturnCode());
		}
	}
}
