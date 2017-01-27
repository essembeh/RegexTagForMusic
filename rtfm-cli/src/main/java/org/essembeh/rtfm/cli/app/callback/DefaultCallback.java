package org.essembeh.rtfm.cli.app.callback;

import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessHelper.Status;

public class DefaultCallback implements ICallback {

	private final Path path;
	private String fullpath;

	public DefaultCallback(Path path) {
		this.path = path;
	}

	public Path getPath() {
		return path;
	}

	public String getFullpath() {
		if (fullpath == null) {
			throw new IllegalStateException();
		}
		return fullpath;
	}

	@Override
	final public void start(String fullpath) {
		this.fullpath = fullpath;
	}

	@Override
	public void unknownType() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fileSkipped(String workflowId, Date lastExecution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void workflowStart(String workflowId, List<String> commands) {
		// TODO Auto-generated method stub

	}

	@Override
	public void workflowDone(String workflowId, boolean complete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void workflowException(String workflowId, Exception e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand, List<String> resolvedCommand) {
		// TODO Auto-generated method stub

	}

	@Override
	public void commandEnds(String commandId, Status status) {
		// TODO Auto-generated method stub

	}

	@Override
	public void done() {
		// TODO Auto-generated method stub

	}

}
