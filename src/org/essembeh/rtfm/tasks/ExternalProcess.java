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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.utils.FileUtils;
import org.essembeh.rtfm.core.utils.ProcessUtils;
import org.essembeh.rtfm.core.utils.ProcessUtils.STDOUT;
import org.essembeh.rtfm.core.workflow.Task;

/**
 * 
 * @author seb
 * 
 */
public class ExternalProcess implements Task {

	static protected Logger logger = Logger.getLogger(ExternalProcess.class);

	String binaryPath;

	Map<String, String> processEnvironment = new HashMap<String, String>();

	@Override
	public void setProperty(String name, String value) {
		if ("binary".equals(name)) {
			try {
				this.binaryPath = retrieveBinaryFullPath(value);
				logger.debug("Using binary: " + this.binaryPath);
			} catch (FileNotFoundException e) {
				logger.error("Cannot find binary: " + this.binaryPath);
			}
		} else if ("env.set".equals(name)) {
			String[] array = value.split("=");
			if (array.length == 2) {
				logger.debug("Environment property: " + array[0] + "=" + array[1]);
				this.processEnvironment.put(array[0], array[1]);
			} else {
				logger.warn("Invalid environment property: " + value);
			}
		} else {
			logger.warn("Invalid property for tagger: " + name + "=" + value);
		}
	}

	protected String retrieveBinaryFullPath(String scriptName) throws FileNotFoundException {
		File script = FileUtils.getResourceAsFile(scriptName);
		String binaryPath = null;
		if (!script.canExecute()) {
			logger.error("The binary is not executable: " + scriptName);
		} else {
			binaryPath = script.getAbsolutePath();
			logger.debug("Found script: " + binaryPath);
		}
		return binaryPath;
	}

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
				logger.debug("STERR:");
				logger.debug(ProcessUtils.getProcessSysOut(process, STDOUT.stderr));
			}
		} catch (IOException e) {
			throw new ActionException(e.getMessage());
		} catch (InterruptedException e) {
			throw new ActionException(e.getMessage());
		} finally {
			ProcessUtils.closeStreams(process);
		}
		return rc;
	}

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

	@Override
	public void execute(MusicFile file) throws ActionException {
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.binaryPath);
		command.add(file.getFile().getAbsolutePath());
		logger.debug("Process to execute: " + StringUtils.join(command, " "));
		// Build env.
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Map<String, String> processBuilderEnv = processBuilder.environment();
		createEnv(processBuilderEnv, file);
		runProcess(processBuilder);
	}

	void createEnv(Map<String, String> processEnv, MusicFile musicFile) {
		for (String key : this.processEnvironment.keySet()) {
			String value = this.processEnvironment.get(key);
			String realValue = valuate(value, musicFile);
			if (realValue != null) {
				logger.debug("Adding to env: " + key + "=" + realValue);
				processEnv.put(key, realValue);
			}
		}
	}

	String valuate(String value, MusicFile musicFile) {
		String attributeValue = value;
		Pattern pattern = Pattern.compile("\\$\\{(.*)\\}");
		Matcher matcher = pattern.matcher(value);
		if (matcher.matches()) {
			String attributeName = matcher.group(1);
			logger.debug("Dynamic value: " + value + ", attribute name: " + attributeName);
			Attribute attribute = musicFile.getAttributeList().get(attributeName);
			if (attribute != null) {
				attributeValue = attribute.getValue();
				logger.debug("Found value: " + attributeValue);
			} else {
				logger.warn("Cannot find attribute: " + attributeName + ", on file: " + musicFile);
			}
		}
		return attributeValue;
	}
}
