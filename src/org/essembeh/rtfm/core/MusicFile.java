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
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.services.Services;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.core.utils.FileUtils;
import org.essembeh.rtfm.interfaces.IMusicFile;

/**
 * Represent a file in Music Folder. Not only MP3 but every file in the folder,
 * covers, playlists ...
 * 
 * @author seb
 * 
 */
public class MusicFile implements Comparable<MusicFile>, IMusicFile {

	/**
	 * 
	 */
	protected static Logger logger = Logger.getLogger(MusicFile.class);

	/**
	 * 
	 */
	protected File file;

	/**
	 * 
	 */
	protected String virtualPath;

	/**
	 * 
	 */
	protected boolean isTagged = false;

	/**
	 * 
	 */
	protected FileHandler handler = null;

	/**
	 * Create a Music File
	 * 
	 * @param file
	 * @throws ConfigurationException
	 */
	public MusicFile(File file) throws ConfigurationException {
		this(file, null);
	}

	/**
	 * Create a Music File
	 * 
	 * @param file
	 * @param rootFolder
	 * @throws ConfigurationException
	 */
	public MusicFile(File file, File rootFolder) throws ConfigurationException {
		this.file = file;
		// Set the virtualPath
		String tmpPath = FileUtils.extractRelativePath(file, rootFolder);
		// For compatibility, replace \ by /
		this.virtualPath = FileUtils.makeNormalPath(tmpPath);
		// Search the corresponding handler
		this.handler = Services.getFilehandlerService().getFileHandlerForFile(getVirtualPath());
		if (this.handler == null) {
			throw new ConfigurationException("Could not find handler for file: " + toString());
		}
		logger.debug("Created MusicFile: " + toString());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.IMusicFile#getTagData()
	 */
	@Override
	public TagData getTagData() throws TagNotFoundException, RTFMException, ConfigurationException {
		TagData tag = this.handler.parseTagData(getVirtualPath());
		return tag;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.IMusicFile#getType()
	 */
	@Override
	public String getType() {
		return this.handler.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.IMusicFile#getVirtualPath()
	 */
	@Override
	public String getVirtualPath() {
		return this.virtualPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.IMusicFile#isTaggable()
	 */
	@Override
	public boolean isTaggable() {
		return this.handler.canTag();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.IMusicFile#isTagged()
	 */
	@Override
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

	/*
	 */
	@Override
	public void tag() throws TagWriterException, TagNotFoundException, RTFMException, ConfigurationException {
		if (!isTaggable()) {
			throw new TagWriterException("This file is not taggable");
		}
		MusicFile.logger.debug("Tag file: " + toString());
		if (this.isTagged()) {
			MusicFile.logger.info("The file is already tagged");
		}
		// Remove the previous tag
		this.handler.removeTag(this.file);
		this.handler.tag(this.file, getTagData());
		MusicFile.logger.debug("File: " + getVirtualPath() + " successfully tagged");
		setTagged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.IMusicFile#isExportableToDatabase()
	 */
	@Override
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
		return getVirtualPath();
	}
}
