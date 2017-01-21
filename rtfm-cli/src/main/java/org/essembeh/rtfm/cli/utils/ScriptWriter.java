package org.essembeh.rtfm.cli.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import org.essembeh.rtfm.cli.app.ProcessHelper;
import org.essembeh.rtfm.cli.app.ProcessHelper.Status;
import org.essembeh.rtfm.cli.app.callback.ICallback;

public class ScriptWriter extends PrintWriter implements ICallback {

	public ScriptWriter(Path script) throws IOException {
		super(Files.newBufferedWriter(script));
		println("#!/bin/sh");
		println("set -e");
		println("set -x");
		println();
	}

	@Override
	public void unknownType(String fullpath) {
	}

	@Override
	public void fileSkipped(String fullpath, String workflowId, Date lastExecution) {
	}

	@Override
	public void fileHandled(String fullpath, String workflowId) {
	}

	@Override
	public void workflowBegins(String workflowId, List<String> commands) {
	}

	@Override
	public void workflowEnds(String workflowId, boolean complete) {
	}

	@Override
	public void workflowException(String workflowId, Exception e) {
	}

	@Override
	public void commandBegins(String commandId, List<String> rawCommand, List<String> resolvedCommand) {
		println(ProcessHelper.escapeCommand(resolvedCommand, '"'));
	}

	@Override
	public void commandEnds(String commandId, Status status) {
	}

	@Override
	public void done(String fullpath) {
	}
}
