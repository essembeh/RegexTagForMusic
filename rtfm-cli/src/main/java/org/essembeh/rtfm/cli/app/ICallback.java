package org.essembeh.rtfm.cli.app;

import java.util.List;

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
