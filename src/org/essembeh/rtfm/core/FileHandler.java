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

import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.services.Services;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.ITagProvider;
import org.essembeh.rtfm.interfaces.ITagWriter;

/**
 * 
 * @author seb
 * 
 */
public class FileHandler {
	/**
	 * The unique identifier of the FileHandler
	 */
	private String id = null;

	/**
	 * The identifier of the tag writer
	 */
	private String tagWriterId;

	/**
	 * The identifier of tag provider
	 */
	private String tagProviderId;

	/**
	 * The pattern to match for the file type.
	 */
	private Pattern applyPattern;

	/**
	 * A flag to write files or not in the Database.
	 */
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
		this.tagProviderId = null;
		this.tagWriterId = null;
	}

	/**
	 * @return the tagProviderId
	 */
	public String getTagProviderId() {
		return this.tagProviderId;
	}

	/**
	 * @param tagProviderId
	 *            the tagProviderId to set
	 */
	public void setTagProviderId(String tagProviderId) {
		this.tagProviderId = tagProviderId;
	}

	/**
	 * @return the applyPattern
	 */
	public Pattern getApplyPattern() {
		return this.applyPattern;
	}

	/**
	 * @param applyPattern
	 *            the applyPattern to set
	 */
	public void setApplyPattern(Pattern applyPattern) {
		this.applyPattern = applyPattern;
	}

	/**
	 * @return the tagWriterId
	 */
	public String getTagWriterId() {
		return this.tagWriterId;
	}

	/**
	 * @param tagWriterId
	 *            the tagWriterId to set
	 */
	public void setTagWriterId(String tagWriterId) {
		this.tagWriterId = tagWriterId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileHandler [id=" + this.id + ", TagWriter=" + this.tagWriterId + ", TagProvider=" + this.tagProviderId
				+ "]";
	}

	/**
	 * Returns true if the file handler can tag its files
	 * 
	 * @return
	 */
	public boolean canTag() {
		return this.tagProviderId != null && this.tagWriterId != null;
	}

	/**
	 * @param mp3
	 * @param tag
	 * @throws TagWriterException
	 * @throws RTFMException
	 * @throws ConfigurationException
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#tag(java.io.File,
	 *      org.essembeh.rtfm.core.tag.TagData, boolean)
	 */
	public void tag(File mp3, TagData tag) throws TagWriterException, RTFMException, ConfigurationException {
		if (!canTag()) {
			throw new RTFMException("Invalid operation on non taggable file: " + toString());
		}
		this.getTagWriter().tag(mp3, tag);
	}

	/**
	 * @param path
	 * @return
	 * @throws TagNotFoundException
	 * @throws RTFMException
	 * @throws ConfigurationException
	 * @see org.essembeh.rtfm.interfaces.ITagProvider#getTagData(java.lang.String)
	 */
	public TagData parseTagData(String path) throws TagNotFoundException, RTFMException, ConfigurationException {
		if (!canTag()) {
			throw new RTFMException("Invalid operation on non taggable file: " + toString());
		}
		return this.getTagProvider().getTagData(path);
	}

	/**
	 * @param mp3
	 * @throws TagWriterException
	 * @throws RTFMException
	 * @throws ConfigurationException
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#removeTag(java.io.File,
	 *      boolean)
	 */
	public void removeTag(File mp3) throws TagWriterException, RTFMException, ConfigurationException {
		if (!canTag()) {
			throw new RTFMException("Invalid operation on non taggable file: " + toString());
		}
		this.getTagWriter().removeTag(mp3);
	}

	/**
	 * Returns the tagWriter.
	 * 
	 * @return
	 * @throws ConfigurationException
	 */
	protected ITagWriter getTagWriter() throws ConfigurationException {
		if (this.tagWriterId == null) {
			throw new ConfigurationException("No TagWriter defined for handler: " + toString());
		}
		ITagWriter tagWriter = Services.getTagWriterService().get(this.tagWriterId);
		if (tagWriter == null) {
			throw new ConfigurationException("Cannot find TagWriter with id: " + this.tagWriterId);
		}
		return tagWriter;
	}

	/**
	 * Returns the tagProvider.
	 * 
	 * @return
	 * @throws ConfigurationException
	 */
	protected ITagProvider getTagProvider() throws ConfigurationException {
		if (this.tagProviderId == null) {
			throw new ConfigurationException("No TagProvider defined for handler: " + toString());
		}
		ITagProvider tagProvider = Services.getTagProviderService().get(this.tagProviderId);
		if (tagProvider == null) {
			throw new ConfigurationException("Cannot find TagProvider with id: " + this.tagProviderId);
		}
		return tagProvider;
	}
}
