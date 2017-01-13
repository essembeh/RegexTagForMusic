package org.essembeh.rtfm.cli.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.essembeh.rtfm.cli.config.Configuration;
import org.essembeh.rtfm.cli.config.Handler;
import org.essembeh.rtfm.cli.resolver.VariableResolver;
import org.essembeh.rtfm.cli.resolver.VariableUtils;
import org.essembeh.rtfm.cli.utils.WorkflowExecutionException;

public class App {

	private final Configuration configuration;
	private final Logger logger;

	boolean resolveEnv = false;
	boolean dryRun = false;

	public App(Configuration configuration, Logger logger) {
		this.configuration = configuration;
		this.logger = logger;
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

	public boolean process(Path in) {
		boolean out = true;
		String fullpath = in.normalize().toString();
		try {
			String filehandlerId = null;
			for (Entry<String, Handler> e : configuration.getTypes().entrySet()) {
				Matcher matcher = Pattern.compile(e.getValue().getPattern()).matcher(fullpath);
				if (matcher.matches()) {
					filehandlerId = e.getKey();
					logger.debug("[%s] %s ", filehandlerId, fullpath);
					executeWorkflow(e.getValue(), matcher);
					logger.info("Finished %s (%s)", fullpath, filehandlerId);
					break;
				}
			}
			if (filehandlerId == null) {
				logger.error("Ignore %s", fullpath);
			}
		} catch (WorkflowExecutionException | IOException | InterruptedException | IllegalStateException e) {
			logger.error("Error with: " + fullpath);
			logger.dump(e);
			out = false;
		}
		return out;
	}

	private void executeWorkflow(Handler fileHandler, Matcher matcher) throws IOException, InterruptedException, WorkflowExecutionException {
		StrSubstitutor resolver = VariableUtils.createResolver(new VariableResolver(matcher, fileHandler.getVariables(), resolveEnv));
		if (fileHandler.getWorkflow().stream().filter(s -> !configuration.getCommands().containsKey(s)).findFirst().isPresent()) {
			throw new IllegalStateException("Invalid workflow: " + StringUtils.join(fileHandler.getWorkflow(), ", "));
		}
		ProcessExecutor executor = new ProcessExecutor(logger, dryRun);
		for (String commandId : fileHandler.getWorkflow()) {
			List<String> rawCommand = configuration.getCommands().get(commandId);
			List<String> resolvedCommand = rawCommand.stream().map(resolver::replace).collect(Collectors.toList());
			logger.debug("  Resolution: %s", rawCommand);
			logger.debug("              %s", resolvedCommand);
			int rc = executor.run(resolvedCommand);
			logger.debug("  Command %s exited with %d", commandId, rc);
			WorkflowExecutionException.check(commandId, rc);
		}
	}
}
