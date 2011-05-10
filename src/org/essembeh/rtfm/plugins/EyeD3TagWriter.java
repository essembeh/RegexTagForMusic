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

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.core.utils.ProcessUtils;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.interfaces.ITagWriter;

public class EyeD3TagWriter implements ITagWriter {

	/**
	 * Used to manage which ID3 tag version to use
	 */
	private enum TagVersion {
		ID3V2_3, ID3V2_4
	}

	private Logger logger = Logger.getLogger(getClass());

	private String binary;

	private String defaultArgs;

	private String removeTagArgs = "--remove-v1 --remove-v2 --remove-all --remove-images --remove-lyrics --remove-comments";

	private boolean forceUtf8 = false;

	private boolean dumpProcessOutput = false;

	protected TagVersion tagVersion = TagVersion.ID3V2_4;

	/**
	 * Execute a command in a Process and return the exit code. If dryrun is
	 * set, the process is not started, 0 is returned.
	 * 
	 * @param command
	 * @param dryRun
	 * @return
	 * @throws TagWriterException
	 */
	protected int executeCommand(List<String> command, boolean dryRun) throws TagWriterException {
		int rc = 0;
		this.logger.debug("Executing command: " + StringUtils.arrayToString(command.toArray(), " "));
		if (dryRun) {
			this.logger.debug("Dry run mode, the command is not executed");
		} else {
			ProcessBuilder pb = new ProcessBuilder(command);
			Process p = null;
			try {
				p = pb.start();
				rc = p.waitFor();
				this.logger.debug("Exit code: " + rc);
				if (rc != 0) {
					this.logger.debug("Proccess exited with value: " + rc);
					if (this.dumpProcessOutput) {
						this.logger.debug("Sysout: " + ProcessUtils.getProcessSysOut(p, false));
						this.logger.debug("Syserr: " + ProcessUtils.getProcessSysOut(p, true));
					}

				}
			} catch (Exception e) {
				throw new TagWriterException(e);
			} finally {
				// Bug "Too many open files": Close all streams
				if (p != null) {
					Closeable c = p.getOutputStream();
					if (c != null) {
						try {
							c.close();
						} catch (IOException e) {
							this.logger.debug("Error while closing stream: " + e.getMessage());
						}
					}
					c = p.getInputStream();
					if (c != null) {
						try {
							c.close();
						} catch (IOException e) {
							this.logger.debug("Error while closing stream: " + e.getMessage());
						}
					}
					c = p.getErrorStream();
					if (c != null) {
						try {
							c.close();
						} catch (IOException e) {
							this.logger.debug("Error while closing stream: " + e.getMessage());
						}
					}
				}
			}
		}
		return rc;
	}

	/**
	 * 
	 * @param mp3
	 * @param dryrun
	 * @throws TagWriterException
	 */
	protected void forceVersion(File mp3, boolean dryrun) throws TagWriterException {
		this.logger.debug("Force Tag Version to: " + this.tagVersion);
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
		int rc = executeCommand(command, dryrun);
		if (rc != 0) {
			throw new TagWriterException("Error setting tag version: " + mp3.getAbsolutePath() + ", rc was: " + rc);
		}
	}

	/**
	 * 
	 * @param mp3
	 * @param dryrun
	 * @throws TagWriterException
	 */
	protected void forceUtf8(File mp3, boolean dryrun) throws TagWriterException {
		this.logger.debug("Force UTF8 for current file tags");
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.binary);
		command.addAll(StringUtils.stringToList(this.defaultArgs, " "));
		command.add("--set-encoding=utf8");
		command.add("--force-update");
		command.add(mp3.getAbsolutePath());
		int rc = executeCommand(command, dryrun);
		if (rc != 0) {
			throw new TagWriterException("Error setting UTF-8 on file: " + mp3.getAbsolutePath() + ", rc was: " + rc);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#removeTag(java.io.File,
	 * boolean)
	 */
	@Override
	public void removeTag(File mp3, boolean dryrun) throws TagWriterException {
		// Build the command
		List<String> command = new ArrayList<String>();
		command.add(this.binary);
		command.addAll(StringUtils.stringToList(this.defaultArgs, " "));
		command.addAll(StringUtils.stringToList(this.removeTagArgs, " "));
		command.add(mp3.getAbsolutePath());

		int rc = executeCommand(command, dryrun);
		if (rc == 0) {
			// OK
		} else if (rc == 255) {
			// OK
			this.logger.debug("Cannot remove inexistant tag");
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
			this.logger.warn("Invalid property for tagger: " + name + "=" + value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#tag(java.io.File,
	 * org.essembeh.rtfm.model.TagData, boolean)
	 */
	@Override
	public boolean tag(File mp3, TagData tag, boolean dryrun) throws TagWriterException {
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
		int rc = executeCommand(command, dryrun);
		if (rc != 0) {
			throw new TagWriterException("Error tagging: " + mp3.getAbsolutePath() + " with: " + tag + ", rc was: "
					+ rc);
		}
		// Set the tag version
		forceVersion(mp3, dryrun);
		// Force UTF8 only option is set
		if (this.forceUtf8 && this.tagVersion == TagVersion.ID3V2_4) {
			forceUtf8(mp3, dryrun);
		}
		return !dryrun;
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
