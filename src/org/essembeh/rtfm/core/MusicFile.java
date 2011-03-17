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

package org.essembeh.rtfm.core;

import java.io.File;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.conf.Services;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.exception.TagWritterException;
import org.essembeh.rtfm.core.tag.TagData;

/**
 * Represent a file in Music Folder. Not only MP3 but every file in the folder,
 * covers, playlists ...
 * 
 * @author seb
 * 
 */
public class MusicFile implements Comparable<MusicFile> {

	private Logger logger = Logger.getLogger(getClass());

	private File file;

	private File rootFolder;

	private boolean isTagged = false;

	private FileHandler handler = null;

	/**
	 * Create a Music File
	 * 
	 * @param file
	 * @param rootFolder
	 * @throws ConfigurationException
	 */
	public MusicFile(File file, File rootFolder) throws ConfigurationException {
		this.file = file;
		this.rootFolder = rootFolder;
		// Search the corresponding handler
		this.handler = Services.instance().getFileHandlerForFile(getVirtualPath());
		if (this.handler == null) {
			throw new ConfigurationException("Could not find handler for file: " + getVirtualPath());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MusicFile o) {
		int ret = getVirtualPath().compareTo(o.getVirtualPath());
		return ret;
	}

	/**
	 * If the file is valid, use the regex tag reader in the handler to read tag
	 * informations from the virtual path.
	 * 
	 * @return
	 * @throws TagNotFoundException
	 * @throws RTFMException
	 */
	public TagData getTagData() throws TagNotFoundException, RTFMException {
		TagData tag = this.handler.getTagData(getVirtualPath());
		return tag;

	}

	/**
	 * Return the ID of handler
	 * 
	 * @return
	 */
	public String getType() {
		return this.handler.getId();
	}

	/**
	 * 
	 * @return
	 */
	public String getVirtualPath() {
		return this.file.getAbsolutePath().replaceFirst(this.rootFolder.getAbsolutePath(), "");
	}

	/**
	 * Returns true if the file is taggable
	 * 
	 * @return
	 */
	public boolean isTaggable() {
		return this.handler.canTag();
	}

	/**
	 * Return true if the file is set as tagged.
	 * 
	 * @return
	 */
	public boolean isTagged() {
		return this.isTagged;
	}

	/**
	 * If the file is taggable, set the flag isTagged to true.
	 */
	public void setTagged() {
		if (isTaggable()) {
			this.isTagged = true;
		}
	}

	/**
	 * 
	 * @param dryrun
	 * @return
	 * @throws TagWritterException
	 * @throws TagNotFoundException
	 * @throws RTFMException
	 */
	public boolean tagFile(boolean dryrun) throws TagWritterException, TagNotFoundException, RTFMException {
		if (!isTaggable()) {
			throw new TagWritterException("This file is not taggable");
		}
		boolean hasBeenTagged = false;
		TagData tag = getTagData();
		this.logger.debug("Tag file: " + toString());
		this.logger.debug("  and tagdata: " + tag);
		if (this.isTagged()) {
			this.logger.debug("The file is already taged");
		}
		this.handler.removeTag(this.file, dryrun);
		hasBeenTagged = this.handler.tag(this.file, tag, dryrun);
		this.logger.debug("File: " + getVirtualPath() + " successfully tagged");
		if (hasBeenTagged) {
			setTagged();
		}
		return hasBeenTagged;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isExportableToDatabase() {
		return this.handler.isExportableToDatabase();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		out.append("[").append(getType()).append("] ");
		if (isTaggable()) {
			if (isTagged()) {
				out.append("+tagged ");
			} else {
				out.append("-tagged ");
			}
		}
		out.append(getVirtualPath());
		return out.toString();
	}
}
