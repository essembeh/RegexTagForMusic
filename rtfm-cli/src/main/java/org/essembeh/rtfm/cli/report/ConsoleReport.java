package org.essembeh.rtfm.cli.report;

import org.essembeh.rtfm.cli.app.ProcessStatus;
import org.essembeh.rtfm.cli.app.callback.DefaultCallback;

public class ConsoleReport extends DefaultCallback {

	@Override
	public void unknownType(String fullpath) {
		System.out.println("UNKNOWN " + fullpath);
	}

	@Override
	public void fileHandled(String fullpath, String filehandlerId) {
		System.out.println(String.format("[%s] %s", filehandlerId, fullpath));
	}

	@Override
	public void workflowException(String workflowId, Exception e) {
		System.out.println("\tEXCEPTION in workflow " + workflowId);
		e.printStackTrace(System.out);
	}

	@Override
	public void commandEnds(String commandId, ProcessStatus status) {
		if (status.getReturnCode() != 0) {
			System.out.println("\tERROR " + commandId + " exited with " + status.getReturnCode());
		}
	}
}
