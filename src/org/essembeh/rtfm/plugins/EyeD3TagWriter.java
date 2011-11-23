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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.core.utils.ProcessUtils;
import org.essembeh.rtfm.core.utils.ProcessUtils.STDOUT;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.interfaces.ITagWriter;

/**
 * 
 * @author seb
 * 
 */
public class EyeD3TagWriter implements ITagWriter {

	/**
	 * Used to manage which ID3 tag version to use
	 */
	protected enum TagVersion {
		ID3V2_3, ID3V2_4
	}

	/**
	 * 
	 */
	static protected Logger logger = Logger.getLogger(EyeD3TagWriter.class);

	/**
	 * 
	 */
	protected String binary;

	/**
	 * 
	 */
	protected String defaultArgs;

	/**
	 * 
	 */
	protected String removeTagArgs = "--remove-v1 --remove-v2 --remove-all --remove-images --remove-lyrics --remove-comments";

	/**
	 * 
	 */
	protected boolean forceUtf8 = false;

	/**
	 * 
	 */
	protected boolean dumpProcessOutput = false;

	/**
	 * 
	 */
	protected TagVersion tagVersion = TagVersion.ID3V2_4;

	/**
	 * Execute a command in a Process and return the exit code.
	 * 
	 * @param command
	 * @return
	 * @throws TagWriterException
	 */
	protected int executeCommand(List<String> command) throws TagWriterException {
		int rc = 0;
		EyeD3TagWriter.logger.debug("Executing command: " + StringUtils.arrayToString(command.toArray(), " "));
		ProcessBuilder pb = new ProcessBuilder(command);
		Process p = null;
		try {
			p = pb.start();
			rc = p.waitFor();
			EyeD3TagWriter.logger.debug("Exit code: " + rc);
			if (rc != 0) {
				EyeD3TagWriter.logger.debug("Proccess exited with value: " + rc);
				if (this.dumpProcessOutput) {
					EyeD3TagWriter.logger.debug("Sysout: " + ProcessUtils.getProcessSysOut(p, STDOUT.stdout));
					EyeD3TagWriter.logger.debug("Syserr: " + ProcessUtils.getProcessSysOut(p, STDOUT.stderr));
				}

			}
		} catch (Exception e) {
			throw new TagWriterException(e);
		} finally {
			// Bug "Too many open files": Close all streams
			ProcessUtils.closeStreams(p);
		}
		return rc;
	}

	/**
	 * 
	 * @param mp3
	 * @throws TagWriterException
	 */
	protected void forceVersion(File mp3) throws TagWriterException {
		EyeD3TagWriter.logger.debug("Force Tag Version to: " + this.tagVersion);
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.binary);
		command.addAll(StringUtils.stringToList(this.defaultArgs, " "));
		switch (this.tagVersion) {
		case ID3V2_3:
			command.add("--to-v2.3");
			break;
		case ID3V2_4:
		default:
			command.add("--to-v2.4");
			break;
		}
		command.add(mp3.getAbsolutePath());
		int rc = executeCommand(command);
		if (rc != 0) {
			throw new TagWriterException("Error setting tag version: " + mp3.getAbsolutePath() + ", rc was: " + rc);
		}
	}

	/**
	 * 
	 * @param mp3
	 * @throws TagWriterException
	 */
	protected void forceUtf8(File mp3) throws TagWriterException {
		EyeD3TagWriter.logger.debug("Force UTF8 for current file tags");
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.binary);
		command.addAll(StringUtils.stringToList(this.defaultArgs, " "));
		command.add("--set-encoding=utf8");
		command.add("--force-update");
		command.add(mp3.getAbsolutePath());
		int rc = executeCommand(command);
		if (rc != 0) {
			throw new TagWriterException("Error setting UTF-8 on file: " + mp3.getAbsolutePath() + ", rc was: " + rc);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#removeTag(java.io.File)
	 */
	@Override
	public void removeTag(File mp3) throws TagWriterException {
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.binary);
		command.addAll(StringUtils.stringToList(this.defaultArgs, " "));
		command.addAll(StringUtils.stringToList(this.removeTagArgs, " "));
		command.add(mp3.getAbsolutePath());

		int rc = executeCommand(command);
		if (rc == 0) {
			// OK
		} else if (rc == 255) {
			// OK
			EyeD3TagWriter.logger.debug("Cannot remove inexistant tag");
		} else {
			throw new TagWriterException("Error removing tag of: " + mp3.getAbsolutePath() + ", rc was: " + rc);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.interfaces.IConfigurableService#setProperty(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void setProperty(String name, String value) {
		if ("eyed3.binary".equals(name)) {
			this.binary = value;
		} else if ("eyed3.default.arguments".equals(name)) {
			this.defaultArgs = value;
		} else if ("force.utf8".equals(name)) {
			this.forceUtf8 = Boolean.parseBoolean(value);
		} else if ("tag.version".equals(name)) {
			this.tagVersion = TagVersion.valueOf(value);
		} else if ("debug.process.ouput".equals(name)) {
			this.dumpProcessOutput = Boolean.parseBoolean(value);
		} else {
			EyeD3TagWriter.logger.warn("Invalid property for tagger: " + name + "=" + value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#tag(java.io.File,
	 * org.essembeh.rtfm.core.tag.TagData)
	 */
	@Override
	public void tag(File mp3, TagData tag) throws TagWriterException {
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.binary);
		command.addAll(StringUtils.stringToList(this.defaultArgs, " "));
		command.add("-v2");
		Map<String, String> map = new HashMap<String, String>();
		map.put("--artist", tag.getArtist());
		map.put("--album", tag.getAlbum());
		map.put("--year", tag.getYear());
		map.put("--track", tag.getTrackNumber());
		map.put("--title", tag.getTrackName());
		if (tag.getComment() != null) {
			map.put("--comment", "::" + tag.getComment());
		}
		for (String key : map.keySet()) {
			String value = map.get(key);
			if (value != null) {
				command.add(key);
				command.add(value);
			}
		}
		command.add(mp3.getAbsolutePath());
		int rc = executeCommand(command);
		if (rc != 0) {
			throw new TagWriterException("Error tagging: " + mp3.getAbsolutePath() + " with: " + tag + ", rc was: "
					+ rc);
		}
		// Set the tag version
		forceVersion(mp3);
		// Force UTF8 only option is set
		if (this.forceUtf8 && this.tagVersion == TagVersion.ID3V2_4) {
			forceUtf8(mp3);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EyeD3TagWriter: " + this.binary + " " + this.defaultArgs;
	}
}
