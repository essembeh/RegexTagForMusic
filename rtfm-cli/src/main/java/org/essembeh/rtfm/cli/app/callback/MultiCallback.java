package org.essembeh.rtfm.cli.app.callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessHelper.Status;

public class MultiCallback implements ICallback {

	private final List<ICallback> callbacks = new ArrayList<>();

	public MultiCallback(ICallback... callbacks) {
		this.callbacks.addAll(Arrays.asList(callbacks));
	}

	@Override
	public void unknownType(String fullpath) {
		callbacks.forEach(c -> c.unknownType(fullpath));
	}

	@Override
	public void fileSkipped(String fullpath, String workflowId, Date lastExecution) {
		callbacks.forEach(c -> c.fileSkipped(fullpath, workflowId, lastExecution));
	}

	@Override
	public void fileHandled(String fullpath, String workflowId) {
		callbacks.forEach(c -> c.fileHandled(fullpath, workflowId));
	}

	@Override
	public void workflowBegins(String workflowId, List<String> commands) {
		callbacks.forEach(c -> c.workflowBegins(workflowId, commands));
	}

	@Override
	public void workflowEnds(String workflowId, boolean complete) {
		callbacks.forEach(c -> c.workflowEnds(workflowId, complete));
	}

	@Override
	public void workflowException(String workflowId, Exception e) {
		callbacks.forEach(c -> c.workflowException(workflowId, e));
	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand, List<String> resolvedCommand) {
		callbacks.forEach(c -> c.commandBegins(commandId, rawCommand, resolvedCommand));
	}

	@Override
	public void commandEnds(String commandId, Status status) {
		callbacks.forEach(c -> c.commandEnds(commandId, status));
	}

	@Override
	public void done(String fullpath) {
		callbacks.forEach(c -> c.done(fullpath));
	}
}
