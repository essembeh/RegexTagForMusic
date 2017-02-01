package org.essembeh.rtfm.core.report;

import java.nio.file.Path;
import java.util.List;

import org.essembeh.rtfm.core.app.ProcessHelper.Status;
import org.essembeh.rtfm.core.app.callback.DefaultCallback;

public class QuietCallback extends DefaultCallback {

	private final ConsoleWriter consoleWriter;

	public QuietCallback(Path path, ConsoleWriter consoleWriter) {
		super(path);
		this.consoleWriter = consoleWriter;
	}

	@Override
	public void unknownType() {
		consoleWriter.print("UNKNOWN " + getFullpath());
	}

	@Override
	public void workflowStart(String workflowId, List<String> commands) {
		consoleWriter.printMessage("[{0}] {1}", workflowId, getFullpath());
	}

	@Override
	public void workflowException(String workflowId, Exception e) {
		consoleWriter.printMessage(String.format("\tEXCEPTION in workflow {0}: {1}", workflowId, e.getMessage()));
	}

	@Override
	public void commandEnds(String commandId, Status status) {
		if (status.getReturnCode() != 0) {
			consoleWriter.print("\tERROR " + commandId + " exited with " + status.getReturnCode());
		}
	}
}
