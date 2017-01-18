package org.essembeh.rtfm.cli.report;

import java.util.List;

import org.essembeh.rtfm.cli.app.ICallback;
import org.essembeh.rtfm.cli.app.ProcessStatus;

public class ConsoleReport implements ICallback {

	@Override
	public void unknownType(String fullpath) {
		System.out.println("UNKNOWN " + fullpath);
	}

	@Override
	public void fileHandled(String fullpath, String filehandlerId) {
		System.out.println(String.format("[%s] %s", filehandlerId, fullpath));
	}

	@Override
	public void workflowException(Exception e) {
		System.out.println("\tEXCEPTION");
		e.printStackTrace(System.out);
	}

	@Override
	public void commandEnds(String commandId, ProcessStatus status) {
		if (status.getReturnCode() != 0) {
			System.out.println("\tERROR " + commandId + " exited with " + status.getReturnCode());
		}
	}

	@Override
	public void workflowBegins(List<String> workflow) {
	}

	@Override
	public void commandBegins(String commandId, List<String> resolvedCommand) {
	}

	@Override
	public void commandResolved(String commandId, List<String> resolvedCommand) {
	}

	@Override
	public void done() {
	}
}
