/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.essembeh.rtfm.tasks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.utils.ProcessUtils;
import org.essembeh.rtfm.core.utils.ProcessUtils.STDOUT;
import org.essembeh.rtfm.core.utils.TaskUtils;

/**
 * 
 * @author seb
 * 
 */
public class ExternalProcess implements ITask {

	static protected Logger logger = Logger.getLogger(ExternalProcess.class);

	private String binaryPath;
	private Map<String, String> properties = new HashMap<String, String>();

	@Override
	public void setProperty(String name, String value) {
		if ("binary".equals(name)) {
			try {
				binaryPath = ProcessUtils.retrieveBinaryFullPath(value);
				logger.debug("Using binary: " + this.binaryPath);
			} catch (FileNotFoundException e) {
				logger.error("Cannot find binary: " + this.binaryPath);
			}
		} else if ("env.set".equals(name)) {
			String[] array = value.split("=");
			if (array.length == 2) {
				logger.debug("Environment property: " + array[0] + "=" + array[1]);
				properties.put(array[0], array[1]);
			} else {
				logger.warn("Invalid environment property: " + value);
			}
		} else {
			logger.warn("Invalid property for tagger: " + name + "=" + value);
		}
	}

	/**
	 * 
	 * @param processBuilder
	 * @return
	 * @throws ActionException
	 */
	protected int runProcess(ProcessBuilder processBuilder) throws ActionException {
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
		} catch (IOException e) {
			throw new ActionException(e.getMessage());
		} catch (InterruptedException e) {
			throw new ActionException(e.getMessage());
		} finally {
			ProcessUtils.end(process);
		}
		return rc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExternalProcess: " + binaryPath;
	}

	/**
	 * Add a key/value in map if the value is not null
	 * 
	 * @param env
	 * @param key
	 * @param value
	 */
	protected static void addIfValueNotNull(Map<String, String> env, String key, String value) {
		if (value != null) {
			env.put(key, value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.core.actions.IRTFMTask#execute(org.essembeh.rtfm.core
	 * .library.file.IMusicFile)
	 */
	@Override
	public void execute(IMusicFile file) throws ActionException {
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.binaryPath);
		command.add(file.getFile().getAbsolutePath());
		logger.debug("Process to execute: " + StringUtils.join(command, " "));
		// Build env.
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		processBuilder.redirectErrorStream(true);
		Map<String, String> processBuilderEnv = processBuilder.environment();
		updateEnvironment(processBuilderEnv, file);
		runProcess(processBuilder);
	}

	/**
	 * 
	 * @param processEnv
	 * @param musicFile
	 */
	void updateEnvironment(Map<String, String> processEnv, IMusicFile musicFile) {
		// Parse env
		for (String key : properties.keySet()) {
			String value = properties.get(key);
			String realValue = TaskUtils.valuateDynamicEnvironmentVariable(value, musicFile);
			if (realValue.length() > 0) {
				processEnv.put(key, realValue);
			}
		}
	}
}
