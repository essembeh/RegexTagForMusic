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
import java.util.regex.Pattern;

import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.exception.TagWritterException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.ITagProvider;
import org.essembeh.rtfm.interfaces.ITagWriter;

public class FileHandler {
	private String id = null;
	private ITagWriter tagWriter = null;
	private ITagProvider tagProvider = null;
	private Pattern applyPattern;
	private boolean isExportableToDatabase = true;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param extension
	 */
	public FileHandler(String id, Pattern applyPattern) {
		this.id = id;
		this.applyPattern = applyPattern;
	}

	/**
	 * 
	 * @param virtualPath
	 * @return
	 */
	public boolean doesApplyForFile(String virtualPath) {
		return this.applyPattern.matcher(virtualPath).matches();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @param tagProvider
	 *            the regexTag to set
	 */
	public void setTagProvider(ITagProvider tagProvider) {
		this.tagProvider = tagProvider;
	}

	/**
	 * @param tagWriter
	 *            the tagWriter to set
	 */
	public void setTagWriter(ITagWriter tagWriter) {
		this.tagWriter = tagWriter;
	}

	/**
	 * Returns true if the file handler can tag its files
	 * 
	 * @return
	 */
	public boolean canTag() {
		return this.tagWriter != null && this.tagProvider != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileHandler [id=" + this.id + ", tagWriter=" + this.tagWriter + ", canTag=" + canTag() + "]";
	}

	/**
	 * @param mp3
	 * @param tag
	 * @param dryrun
	 * @return
	 * @throws TagWritterException
	 * @throws RTFMException
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#tag(java.io.File,
	 *      org.essembeh.rtfm.core.tag.TagData, boolean)
	 */
	public boolean tag(File mp3, TagData tag, boolean dryrun) throws TagWritterException, RTFMException {
		if (!canTag()) {
			throw new RTFMException("Invalid operation on non taggable file: " + toString());
		}
		return tagWriter.tag(mp3, tag, dryrun);
	}

	/**
	 * @param path
	 * @return
	 * @throws TagNotFoundException
	 * @throws RTFMException
	 * @see org.essembeh.rtfm.interfaces.ITagProvider#getTagData(java.lang.String)
	 */
	public TagData getTagData(String path) throws TagNotFoundException, RTFMException {
		if (!canTag()) {
			throw new RTFMException("Invalid operation on non taggable file: " + toString());
		}
		return tagProvider.getTagData(path);
	}

	/**
	 * @param mp3
	 * @param dryrun
	 * @throws TagWritterException
	 * @throws RTFMException
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#removeTag(java.io.File,
	 *      boolean)
	 */
	public void removeTag(File mp3, boolean dryrun) throws TagWritterException, RTFMException {
		if (!canTag()) {
			throw new RTFMException("Invalid operation on non taggable file: " + toString());
		}
		tagWriter.removeTag(mp3, dryrun);
	}

	/**
	 * If true, the file will be exported while writting database.
	 * 
	 * @param value
	 */
	public void setExportableToDatabase(boolean value) {
		this.isExportableToDatabase = value;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isExportableToDatabase() {
		return this.isExportableToDatabase;
	}
}
