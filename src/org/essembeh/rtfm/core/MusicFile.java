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
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.IMusicFile;

/**
 * Represent a file in Music Folder. Not only MP3 but every file in the folder,
 * covers, playlists ...
 * 
 * @author seb
 * 
 */
public class MusicFile implements Comparable<MusicFile>, IMusicFile {

	protected static Logger logger = Logger.getLogger(MusicFile.class);

	protected File file;

	protected File rootFolder;

	protected boolean isTagged = false;

	protected FileHandler handler = null;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.IMusicFile#getTagData()
	 */
	@Override
	public TagData getTagData() throws TagNotFoundException, RTFMException {
		TagData tag = this.handler.getTagData(getVirtualPath());
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
		return this.file.getAbsolutePath().replaceFirst(this.rootFolder.getAbsolutePath(), "");
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

	/**
	 * 
	 * @param dryrun
	 * @return
	 * @throws TagWriterException
	 * @throws TagNotFoundException
	 * @throws RTFMException
	 */
	@Override
	public boolean tag(boolean dryrun) throws TagWriterException, TagNotFoundException, RTFMException {
		if (!isTaggable()) {
			throw new TagWriterException("This file is not taggable");
		}
		boolean hasBeenTagged = false;
		TagData tag = getTagData();
		MusicFile.logger.debug("Tag file: " + toString());
		MusicFile.logger.debug("  and tagdata: " + tag);
		if (this.isTagged()) {
			MusicFile.logger.info("The file is already taged");
		}
		this.handler.removeTag(this.file, dryrun);
		hasBeenTagged = this.handler.tag(this.file, tag, dryrun);
		MusicFile.logger.debug("File: " + getVirtualPath() + " successfully tagged");
		if (hasBeenTagged) {
			setTagged();
		}
		return hasBeenTagged;
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
