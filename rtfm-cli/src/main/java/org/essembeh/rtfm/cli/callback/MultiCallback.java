package org.essembeh.rtfm.cli.callback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessStatus;

public class MultiCallback implements ICallback {

	private final List<ICallback> callbacks = new ArrayList<>();

	public MultiCallback(ICallback... callbacks) {
		this.callbacks.addAll(Arrays.asList(callbacks));
	}

	@Override
	public void unknownType(String fullpath) {
		callbacks.forEach(l -> l.unknownType(fullpath));
	}

	@Override
	public void fileHandled(String fullpath, String filehandlerId) {
		callbacks.forEach(l -> l.fileHandled(fullpath, filehandlerId));
	}

	@Override
	public void workflowBegins(List<String> workflow) {
		callbacks.forEach(l -> l.workflowBegins(workflow));
	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand) {
		callbacks.forEach(l -> l.commandBegins(commandId, rawCommand));
	}

	@Override
	public void commandResolved(String commandId, List<String> resolvedCommand) {
		callbacks.forEach(l -> l.commandResolved(commandId, resolvedCommand));
	}

	@Override
	public void commandEnds(String commandId, ProcessStatus result) {
		callbacks.forEach(l -> l.commandEnds(commandId, result));
	}

	@Override
	public void workflowException(Exception e) {
		callbacks.forEach(l -> l.workflowException(e));
	}

	@Override
	public void done() {
		callbacks.forEach(l -> l.done());
	}
}
