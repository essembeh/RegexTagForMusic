package org.essembeh.rtfm.core.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.essembeh.rtfm.core.app.ProcessHelper.Status;
import org.essembeh.rtfm.core.app.callback.ICallback;
import org.essembeh.rtfm.core.config.Configuration;
import org.essembeh.rtfm.core.config.Workflow;
import org.essembeh.rtfm.core.db.Database;
import org.essembeh.rtfm.core.resolver.BuiltinVariables;
import org.essembeh.rtfm.core.resolver.VariablesUtils;

public class App {

	private final Configuration configuration;
	private Database database = new Database();
	boolean resolveEnv = false;
	boolean dryRun = false;
	private boolean executeAllWorkflows = false;

	public App(Configuration configuration) {
		this.configuration = configuration;
		configuration.check();
	}

	public boolean dryRun() {
		return dryRun;
	}

	public boolean resolveEnv() {
		return resolveEnv;
	}

	public void setDryRun(boolean dryRun) {
		this.dryRun = dryRun;
	}

	public void setResolveEnv(boolean resolveEnv) {
		this.resolveEnv = resolveEnv;
	}

	public Runnable processLater(Path in, ICallback callback) {
		return new Runnable() {
			@Override
			public void run() {
				process(in, callback);
			}
		};
	}

	public void process(Path in, ICallback callback) {
		String fullpath = BuiltinVariables.PATH.resolve(in);
		callback.start(fullpath);
		int matchingTypes = 0;
		for (Entry<String, Workflow> entry : configuration.getWorkflows().entrySet()) {
			Matcher m = Pattern.compile(entry.getValue().getPattern()).matcher(fullpath);
			if (m.matches()) {
				matchingTypes++;
				Optional<Date> lastExecution = database.getExecutionDate(in, entry.getKey());
				if (lastExecution.isPresent()) {
					callback.fileSkipped(entry.getKey(), lastExecution.get());
				} else {
					try {
						executeWorkflow(entry.getKey(), entry.getValue(), in, m, callback);
					} catch (Exception e) {
						callback.workflowException(entry.getKey(), e);
					}
				}
				if (!executeAllWorkflows) {
					break;
				}
			}
		}
		if (matchingTypes == 0) {
			callback.unknownType();
		}
		callback.done();
	}

	protected void executeWorkflow(String workflowId, Workflow workflow, Path in, Matcher matcher, ICallback callback)
			throws IOException, InterruptedException {
		callback.workflowStart(workflowId, workflow.getExecute());
		// Resolution
		StrSubstitutor resolver = VariablesUtils.createVariableResolver(in, matcher, workflow.getVariables(), resolveEnv);
		List<ProcessHelper> processHelpers = new ArrayList<>();
		for (String commandId : workflow.getExecute()) {
			List<String> rawCommand = configuration.getCommands().get(commandId);
			ProcessHelper processHelper = new ProcessHelper(commandId, rawCommand, VariablesUtils.resolveCommand(commandId, resolver, rawCommand));
			processHelpers.add(processHelper);
		}
		// Execution
		boolean complete = true;
		for (ProcessHelper processHelper : processHelpers) {
			callback.commandBegins(processHelper.getCommandId(), processHelper.getRawCommand(), processHelper.getResolvedCommand());
			Status status = processHelper.execute(dryRun);
			callback.commandEnds(processHelper.getCommandId(), status);
			if (!status.isOk()) {
				complete = false;
				break;
			}
		}
		if (complete) {
			database.logWorkflowSuccess(in, workflowId);
		}
		callback.workflowDone(workflowId, complete);
	}

	public void loadDatabase(Path path) throws IOException {
		this.database.load(path);
	}

	public void saveDatabase(Path output) throws IOException {
		this.database.save(output);
	}

	public Database getDatabase() {
		return database;
	}

	public void setExecuteAllWorkflows(boolean executeAllWorkflows) {
		this.executeAllWorkflows = executeAllWorkflows;
	}
}
