package org.essembeh.rtfm.cli.app;

import java.util.List;

public class DefaultCallback implements ICallback {

	@Override
	public void unknownType(String fullpath) {
	}

	@Override
	public void fileHandled(String fullpath, String filehandlerId) {
	}

	@Override
	public void workflowBegins(List<String> workflow) {
	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand) {
	}

	@Override
	public void commandResolved(String commandId, List<String> resolvedCommand) {
	}

	@Override
	public void commandEnds(String commandId, ProcessStatus result) {
	}

	@Override
	public void workflowException(Exception e) {
	}

	@Override
	public void done() {
	}

}
