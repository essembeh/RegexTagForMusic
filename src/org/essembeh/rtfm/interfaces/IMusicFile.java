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

import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.exception.TagWriterException;
import org.essembeh.rtfm.core.tag.TagData;

/**
 * 
 * @author seb
 * 
 */
public interface IMusicFile {

	/**
	 * If the file is valid, use the regex tag reader in the handler to read tag
	 * informations from the virtual path.
	 * 
	 * @return
	 * @throws TagNotFoundException
	 * @throws RTFMException
	 * @throws ConfigurationException
	 */
	public TagData getTagData() throws TagNotFoundException, RTFMException, ConfigurationException;

	/**
	 * Tag the file.
	 * 
	 * @param dryrun
	 * @return
	 * @throws TagWriterException
	 * @throws TagNotFoundException
	 * @throws RTFMException
	 * @throws ConfigurationException
	 */
	public boolean tag(boolean dryrun) throws TagWriterException, TagNotFoundException, RTFMException,
			ConfigurationException;

	/**
	 * Return the ID of handler
	 * 
	 * @return
	 */
	public String getType();

	/**
	 * 
	 * @return
	 */
	public String getVirtualPath();

	/**
	 * Returns true if the file is taggable
	 * 
	 * @return
	 */
	public boolean isTaggable();

	/**
	 * Return true if the file is set as tagged.
	 * 
	 * @return
	 */
	public boolean isTagged();

	/**
	 * 
	 * @return
	 */
	public boolean isExportableToDatabase();

}
