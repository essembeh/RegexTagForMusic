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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.file.attributes.Attributes;

/**
 * Represent a file with attributes
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
	private final Attributes attributes;

	/**
	 * 
	 * @param type
	 * @param virtualFile
	 */
	public XFile(FileType type, VirtualFile virtualFile) {
		this.type = type;
		this.file = virtualFile;
		this.attributes = new Attributes();
	}

	@Override
	public FileType getType() {
		return type;
	}

	@Override
	public String getVirtualPath() {
		return file.getVirtualPath();
	}

	@Override
	public int compareTo(IXFile other) {
		return getVirtualPath().compareTo(other.getVirtualPath());
	}

	@Override
	public VirtualFile getFile() {
		return file;
	}

	@Override
	public Attributes getAttributes() {
		return attributes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
