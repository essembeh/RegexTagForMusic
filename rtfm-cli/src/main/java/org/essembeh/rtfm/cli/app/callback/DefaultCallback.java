package org.essembeh.rtfm.cli.app.callback;

import java.util.Date;
import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessStatus;

public class DefaultCallback implements ICallback {

	@Override
	public void unknownType(String fullpath) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileSkipped(String fullpath, String workflowId, Date lastExecution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileHandled(String fullpath, String workflowId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void workflowBegins(String workflowId, List<String> commands) {
		// TODO Auto-generated method stub

	}

	@Override
	public void workflowEnds(String workflowId, boolean complete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void workflowException(String workflowId, Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commandResolved(String commandId, List<String> resolvedCommand) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commandEnds(String commandId, ProcessStatus result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void done(String fullpath) {
		// TODO Auto-generated method stub

	}

}
