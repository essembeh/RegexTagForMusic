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

package org.essembeh.rtfm.plugins;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.core.utils.ProcessUtils;
import org.essembeh.rtfm.core.utils.ProcessUtils.STDOUT;
import org.essembeh.rtfm.interfaces.ITagWriter;

/**
 * 
 * @author seb
 * 
 */
public class ExternalScriptTagWriter implements ITagWriter {

	/**
	 * 
	 */
	static protected Logger logger = Logger.getLogger(ExternalScriptTagWriter.class);

	/**
	 * 
	 */
	protected String removeTagScript;
	protected String tagScript;

	/**
	 * 
	 */
	protected Map<String, String> addToEnv = new HashMap<String, String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.interfaces.IConfigurableService#setProperty(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void setProperty(String name, String value) {
		if ("script.tag".equals(name)) {
			this.tagScript = retrieveScript(value);
			logger.debug("Using tag script: " + this.tagScript);
		} else if ("script.removetag".equals(name)) {
			this.removeTagScript = retrieveScript(value);
			logger.debug("Using remove tag script: " + this.removeTagScript);
		} else if ("env.set".equals(name)) {
			String[] array = value.split("=");
			if (array.length == 2) {
				logger.debug("Adding to env: " + array[0] + "=" + array[1]);
				this.addToEnv.put(array[0], array[1]);
			} else {
				logger.warn("Cannot add to env: " + value);
			}
		} else {
			logger.warn("Invalid property for tagger: " + name + "=" + value);
		}
	}

	/**
	 * Retrieve the full path of a file in classpath
	 * 
	 * @param scriptName
	 * @return
	 */
	protected String retrieveScript(String scriptName) {
		URL urlOfScript = Thread.currentThread().getContextClassLoader().getResource(scriptName);
		logger.debug("URL of script: " + urlOfScript);
		return urlOfScript.getFile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#tag(java.io.File,
	 * org.essembeh.rtfm.model.TagData, boolean)
	 */
	@Override
	public boolean tag(File mp3, TagData tag, boolean dryrun) throws TagWriterException {
		logger.debug("Tagging file: " + mp3 + ", with data: " + tag);
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.tagScript);
		command.add(mp3.getAbsolutePath());
		// Build env.
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Map<String, String> processBuilderEnv = processBuilder.environment();
		addIfValueNotNull(processBuilderEnv, "RTFM_ARTIST", tag.getArtist());
		addIfValueNotNull(processBuilderEnv, "RTFM_ALBUM", tag.getAlbum());
		addIfValueNotNull(processBuilderEnv, "RTFM_YEAR", tag.getYear());
		addIfValueNotNull(processBuilderEnv, "RTFM_TRACKNAME", tag.getTrackName());
		addIfValueNotNull(processBuilderEnv, "RTFM_TRACKNUMBER", tag.getTrackNumber());
		addIfValueNotNull(processBuilderEnv, "RTFM_COMMENT", tag.getComment());
		// Set custom env
		for (String key : this.addToEnv.keySet()) {
			processBuilderEnv.put(key, this.addToEnv.get(key));
		}
		runProcess(processBuilder);
		return !dryrun;
	}

	/**
	 * Runs a process
	 * 
	 * @param processBuilder
	 * @throws TagWriterException
	 */
	protected void runProcess(ProcessBuilder processBuilder) throws TagWriterException {
		// Launch the process
		Process process = null;
		try {
			// Launch the process
			process = processBuilder.start();
			// Wait for the end
			int processReturnCode = process.waitFor();
			// Show stdout, stderr
			if (logger.isDebugEnabled()) {
				logger.debug("STDOUT:");
				logger.debug(ProcessUtils.getProcessSysOut(process, STDOUT.stdout));
				logger.debug("STERR:");
				logger.debug(ProcessUtils.getProcessSysOut(process, STDOUT.stderr));
			}
			// Close IOs
			if (processReturnCode != 0) {
				throw new TagWriterException("Error, the return code is: " + processReturnCode);
			}
		} catch (IOException e) {
			throw new TagWriterException(e.getMessage());
		} catch (InterruptedException e) {
			throw new TagWriterException(e.getMessage());
		} finally {
			ProcessUtils.closeStreams(process);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ExternalScriptTagWriter";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#removeTag(java.io.File,
	 * boolean)
	 */
	@Override
	public void removeTag(File mp3, boolean dryrun) throws TagWriterException {
		logger.debug("Removing tags from file: " + mp3);
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.removeTagScript);
		command.add(mp3.getAbsolutePath());
		// Build env.
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		Map<String, String> processBuilderEnv = processBuilder.environment();
		// Set custom env
		for (String key : this.addToEnv.keySet()) {
			processBuilderEnv.put(key, this.addToEnv.get(key));
		}
		runProcess(processBuilder);
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
}
