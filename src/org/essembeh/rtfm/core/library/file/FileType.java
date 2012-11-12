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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileType implements Comparable<FileType> {

	private final static List<FileType> knownFiletypes = new ArrayList<FileType>();

	/**
	 * Returns all known Filetypes
	 * 
	 * @return
	 */
	public static List<FileType> getAllFileTypes() {
		return Collections.unmodifiableList(knownFiletypes);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static FileType getFiletype(String id) {
		for (FileType fileType : knownFiletypes) {
			if (fileType.getIdentifier().equals(id)) {
				return fileType;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public static FileType createFiletype(String id) {
		FileType newFileType = getFiletype(id);
		if (newFileType == null) {
			newFileType = new FileType(id);
			knownFiletypes.add(newFileType);
		}
		return newFileType;
	}

	/**
	 * 
	 */
	private final String identifier;

	/**
	 * 
	 * @param id
	 */
	private FileType(String id) {
		identifier = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getIdentifier() {
		return identifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return identifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof FileType && identifier.equals(((FileType) obj).getIdentifier());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(FileType o) {
		return identifier.compareTo(o.getIdentifier());
	}
}
