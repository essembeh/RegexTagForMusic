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

package org.essembeh.rtfm.interfaces;

import java.io.File;

import org.essembeh.rtfm.core.exception.TagWritterException;
import org.essembeh.rtfm.core.tag.TagData;

public interface ITagWritter {

	/**
	 * 
	 * @param mp3
	 * @param dryrun
	 * @throws TagWritterException
	 */
	public void removeTag(File mp3, boolean dryrun) throws TagWritterException;

	/**
	 * Configure the service with the given property
	 * 
	 * @param name
	 * @param value
	 */
	void setProperty(String name, String value);

	/**
	 * 
	 * @param mp3
	 * @param tag
	 * @param dryrun
	 * @return
	 * @throws TagWritterException
	 */
	public boolean tag(File mp3, TagData tag, boolean dryrun) throws TagWritterException;
}
