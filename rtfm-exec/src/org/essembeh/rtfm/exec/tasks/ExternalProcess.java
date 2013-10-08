package org.essembeh.rtfm.exec.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.exec.utils.CommonExecutable;
import org.essembeh.rtfm.exec.utils.ProcessUtils;
import org.essembeh.rtfm.exec.utils.ProcessUtils.STDOUT;
import org.essembeh.rtfm.exec.utils.PropertyUtils;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

/**
 * 
 * @author seb
 * 
 */
public class ExternalProcess extends CommonExecutable implements IExecutable {

	static private Logger logger = Logger.getLogger(ExternalProcess.class);

	public ExternalProcess() {
		logger.debug("New external process");
	}

	@Override
	protected int execute0(IResource resource) throws ExecutionException {
		// Build the command
		List<String> command = new ArrayList<String>();
		// Args
		for (String arg : getPropertiesByKey("command")) {
			command.add(PropertyUtils.valuateDynamicEnvironmentVariable(arg, resource));
		}
		logger.debug("Command to execute: " + StringUtils.join(command, " "));
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		processBuilder.environment().putAll(initEnv(resource));
		return runProcess(processBuilder);
	}

	protected int runProcess(ProcessBuilder processBuilder) throws ExecutionException {
		int rc = 0;
		// Launch the process
		Process process = null;
		try {
			// Launch the process
			process = processBuilder.start();
			// Wait for the end
			rc = process.waitFor();
			// Show stdout, stderr
			if (logger.isDebugEnabled()) {
				logger.debug("STDOUT:");
				logger.debug(ProcessUtils.getProcessSysOut(process, STDOUT.stdout));
			}
		} catch (IOException | InterruptedException e) {
			throw new ExecutionException(e.getMessage());
		} finally {
			ProcessUtils.end(process);
		}
		return rc;
	}

	protected Map<String, String> initEnv(IResource resource) {
		Map<String, String> out = new HashMap<String, String>();
		for (String value : getPropertiesByKey("env")) {
			Pair<String, String> p = PropertyUtils.stringToPair(value);
			if (p.getKey() != null && p.getValue() != null) {
				String key = p.getKey();
				String customValue = PropertyUtils.valuateDynamicEnvironmentVariable(p.getValue(), resource);
				logger.debug("Environment property: " + key + "=" + customValue);
				out.put(key, customValue);
			} else {
				logger.warn("Invalid env property: " + value);
			}
		}
		return out;
	}

	protected static void addIfValueNotNull(Map<String, String> env, String key, String value) {
		if (value != null) {
			env.put(key, value);
		}
	}
}
