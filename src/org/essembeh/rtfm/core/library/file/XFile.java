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

package org.essembeh.rtfm.core.library.file;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.identifiers.AttributeIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

/**
 * Represent a file in Music Folder. Not only MP3 but every file in the folder, covers, playlists ...
 * 
 * @author seb
 * 
 */
public class XFile implements IXFile {

	/**
	 * Attributes
	 */
	protected static Logger logger = Logger.getLogger(XFile.class);

	private final FileType type;
	private final VirtualFile file;
	private final IdList<Attribute, Identifier<Attribute>> attributeList;

	/**
	 * 
	 * @param type
	 * @param virtualFile
	 */
	public XFile(FileType type, VirtualFile virtualFile) {
		this.type = type;
		this.file = virtualFile;
		this.attributeList = new IdList<Attribute, Identifier<Attribute>>(new AttributeIdentifier());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.file.IXFile#getType()
	 */
	@Override
	public FileType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.file.IVirtualFile#getVirtualPath()
	 */
	@Override
	public String getVirtualPath() {
		return file.getVirtualPath();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IXFile other) {
		return getVirtualPath().compareTo(other.getVirtualPath());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.file.IVirtualFile#getFile()
	 */
	@Override
	public VirtualFile getFile() {
		return file;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.file.IXFile#getAttributeList()
	 */
	@Override
	public IdList<Attribute, Identifier<Attribute>> getAttributeList() {
		return attributeList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "XFile [type=" + type + ", file=" + file + ", attributeList=" + StringUtils.join(attributeList, ", ") + "]";
	}
}
