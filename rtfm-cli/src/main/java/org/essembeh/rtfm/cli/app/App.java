package org.essembeh.rtfm.cli.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.essembeh.rtfm.cli.app.ProcessHelper.Status;
import org.essembeh.rtfm.cli.app.callback.ICallback;
import org.essembeh.rtfm.cli.config.Configuration;
import org.essembeh.rtfm.cli.config.Workflow;
import org.essembeh.rtfm.cli.db.Database;
import org.essembeh.rtfm.cli.resolver.BuiltinVariables;
import org.essembeh.rtfm.cli.resolver.VariablesUtils;

public class App {

	private final Configuration configuration;
	private Database database = new Database();
	boolean resolveEnv = false;
	boolean dryRun = false;
	private boolean executeAllWorkflows = false;

	public App(Configuration configuration) {
		this.configuration = configuration;
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

	public void process(Stream<Path> stream, Function<Path, ICallback> callbackFactory) {
		stream.forEach(p -> process(p, callbackFactory.apply(p)));
	}

	public void process(Path in, ICallback callback) {
		String fullpath = BuiltinVariables.PATH.resolve(in);
		int matchingTypes = 0;
		for (Entry<String, Workflow> entry : configuration.getWorkflows().entrySet()) {
			Matcher m = Pattern.compile(entry.getValue().getPattern()).matcher(fullpath);
			if (m.matches()) {
				matchingTypes++;
				Optional<Date> lastExecution = database.getExecutionDate(in, entry.getKey());
				if (lastExecution.isPresent()) {
					callback.fileSkipped(fullpath, entry.getKey(), lastExecution.get());
				} else {
					callback.fileHandled(fullpath, entry.getKey());
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
			callback.unknownType(fullpath);
		}
		callback.done(fullpath);
	}

	protected void executeWorkflow(String workflowId, Workflow workflow, Path in, Matcher matcher, ICallback callback)
			throws IOException, InterruptedException {
		callback.workflowBegins(workflowId, workflow.getExecute());
		// Resolution
		StrSubstitutor resolver = VariablesUtils.createVariableResolver(in, matcher, workflow.getVariables(), resolveEnv);
		List<ProcessHelper> processHelpers = new ArrayList<>();
		for (String commandId : workflow.getExecute()) {
			List<String> rawCommand = configuration.getCommands().get(workflowId);
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
		callback.workflowEnds(workflowId, complete);
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
