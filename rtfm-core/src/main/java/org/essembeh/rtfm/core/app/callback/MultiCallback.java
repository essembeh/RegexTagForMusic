package org.essembeh.rtfm.core.app.callback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.essembeh.rtfm.core.app.ProcessHelper.Status;

public class MultiCallback implements ICallback {

	public static MultiCallback with(ICallback... callbacks) {
		MultiCallback out = new MultiCallback();
		return out.add(callbacks);
	}

	private final List<ICallback> callbacks = new ArrayList<>();

	public MultiCallback add(ICallback... callbacks) {
		for (ICallback i : callbacks) {
			if (i != null) {
				this.callbacks.add(i);
			}
		}
		return this;
	}

	@Override
	public void start(String fullpath) {
		callbacks.forEach(c -> c.start(fullpath));
	}

	@Override
	public void unknownType() {
		callbacks.forEach(ICallback::unknownType);
	}

	@Override
	public void fileSkipped(String workflowId, Date lastExecution) {
		callbacks.forEach(c -> c.fileSkipped(workflowId, lastExecution));
	}

	@Override
	public void workflowStart(String workflowId, List<String> commands) {
		callbacks.forEach(c -> c.workflowStart(workflowId, commands));
	}

	@Override
	public void workflowDone(String workflowId, boolean complete) {
		callbacks.forEach(c -> c.workflowDone(workflowId, complete));
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
	public void done() {
		callbacks.forEach(ICallback::done);
	}
}
