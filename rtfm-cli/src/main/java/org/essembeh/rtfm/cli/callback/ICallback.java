package org.essembeh.rtfm.cli.callback;

import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessStatus;

public interface ICallback {

	void unknownType(String fullpath);

	void fileHandled(String fullpath, String filehandlerId);
	
	void workflowBegins(List<String> workflow);
	
	void commandBegins(String commandId, List<String> rawCommand);

	void commandResolved(String commandId, List<String> resolvedCommand);

	void commandEnds(String commandId, ProcessStatus result);

	void workflowException(Exception e);
	
	void done();
}
