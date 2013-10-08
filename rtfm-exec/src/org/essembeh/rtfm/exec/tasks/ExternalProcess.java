package org.essembeh.rtfm.exec.tasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.exec.utils.ProcessUtils;
import org.essembeh.rtfm.exec.utils.ProcessUtils.STDOUT;
import org.essembeh.rtfm.exec.utils.PropertyUtils;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

/**
 * 
 * @author seb
 * 
 */
public class ExternalProcess implements IExecutable {

	static protected Logger logger = Logger.getLogger(ExternalProcess.class);

	private final List<String> commandList;
	private final List<Pair<String, String>> envList;
	private Integer userReturnCode = null;

	public ExternalProcess() {
		logger.debug("New external process");
		commandList = new ArrayList<>();
		envList = new ArrayList<>();
	}

	@Override
	public void setProperty(String name, String value) {
		if ("env".equals(name)) {
			Pair<String, String> p = PropertyUtils.stringToPair(value);
			if (p.getKey() != null && p.getValue() != null) {
				logger.debug("Environment property: " + p.getKey() + "=" + p.getValue());
				envList.add(p);
			} else {
				logger.warn("Invalid property: " + name + " = " + value);
			}
		} else if ("command".equals(name)) {
			logger.debug("Command: " + value);
			commandList.add(value);
		} else if ("rc".equals(name)) {
			logger.debug("Return code: " + value);
			userReturnCode = Integer.parseInt(value);
		} else {
			logger.warn("Invalid property for tagger: " + name + "=" + value);
		}
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

	@Override
	public String toString() {
		return "ExternalProcess: " + StringUtils.join(commandList, " ");
	}

	protected static void addIfValueNotNull(Map<String, String> env, String key, String value) {
		if (value != null) {
			env.put(key, value);
		}
	}

	@Override
	public int execute(IResource resource) throws ExecutionException {
		// Build the command
		List<String> command = new ArrayList<String>();
		// Args
		for (String arg : commandList) {
			command.add(PropertyUtils.valuateDynamicEnvironmentVariable(arg, resource));
		}
		logger.debug("Command to execute: " + StringUtils.join(command, " "));
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		for (Pair<String, String> pair : envList) {
			processBuilder.environment().put(pair.getKey(),
					PropertyUtils.valuateDynamicEnvironmentVariable(pair.getValue(), resource));
		}
		int rc = runProcess(processBuilder);
		return userReturnCode == null ? rc : userReturnCode;
	}
}
