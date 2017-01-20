package org.essembeh.rtfm.cli.app.callback;

import java.util.Date;
import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessStatus;

public interface ICallback {

	void unknownType(String fullpath);

	void fileSkipped(String fullpath, String workflowId, Date lastExecution);

	void fileHandled(String fullpath, String workflowId);

	void workflowBegins(String workflowId, List<String> commands);

	void workflowEnds(String workflowId, boolean complete);

	void workflowException(String workflowId, Exception e);

	void commandBegins(String commandId, List<String> rawCommand);

	void commandResolved(String commandId, List<String> resolvedCommand);

	void commandEnds(String commandId, ProcessStatus result);

	void done(String fullpath);

}
