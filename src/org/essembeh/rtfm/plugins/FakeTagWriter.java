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
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.ITagWriter;

/**
 * 
 * @author seb
 * 
 */
public class FakeTagWriter implements ITagWriter {

	/**
	 * 
	 */
	List<File> listOfModifiedFiles = new ArrayList<File>();

	/**
	 * 
	 */
	Logger logger = Logger.getLogger(getClass());

	long sleep = 0l;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#removeTag(java.io.File)
	 */
	@Override
	public void removeTag(File mp3) {
		this.logger.debug("Removing tags from file: " + mp3.getAbsolutePath());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.interfaces.ITagWriter#setProperty(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setProperty(String name, String value) {
		this.logger.debug("Trying to set property fo FakeTagWriter: " + name + "=" + value);
		if ("sleep".equals(name)) {
			this.sleep = Long.parseLong(value);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ITagWriter#tag(java.io.File,
	 * org.essembeh.rtfm.core.tag.TagData)
	 */
	@Override
	public void tag(File mp3, TagData tag) {
		this.logger.debug("Tagging file: " + mp3.getAbsolutePath());
		this.logger.debug(" with tags: " + tag);
		if (!this.listOfModifiedFiles.contains(mp3)) {
			this.listOfModifiedFiles.add(mp3);
		}
		try {
			Thread.sleep(this.sleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
