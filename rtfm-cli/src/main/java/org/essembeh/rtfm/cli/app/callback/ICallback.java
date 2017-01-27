package org.essembeh.rtfm.cli.app.callback;

import java.util.Date;
import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessHelper.Status;

public interface ICallback {

	void start(String fullpath);

	void unknownType();

	void fileSkipped(String workflowId, Date lastExecution);

	void workflowStart(String workflowId, List<String> commands);

	void workflowDone(String workflowId, boolean complete);

	void workflowException(String workflowId, Exception e);

	void commandBegins(String commandId, List<String> rawCommand, List<String> resolvedCommand);

	void commandEnds(String commandId, Status status);

	void done();

}
