package org.essembeh.rtfm.cli.report;

import org.essembeh.rtfm.cli.app.ProcessHelper.Status;
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
		System.out.println(String.format("\tEXCEPTION in workflow %s: %s", workflowId, e.getMessage()));
	}

	@Override
	public void commandEnds(String commandId, Status status) {
		if (status.getReturnCode() != 0) {
			System.out.println("\tERROR " + commandId + " exited with " + status.getReturnCode());
		}
	}
}
