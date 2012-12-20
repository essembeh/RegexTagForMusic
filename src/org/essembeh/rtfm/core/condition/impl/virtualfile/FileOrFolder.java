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
package org.essembeh.rtfm.core.condition.impl.virtualfile;

import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.library.file.IVirtualFile;

public class FileOrFolder<T extends IVirtualFile> implements ICondition<T> {

	/**
	 * 
	 */
	public enum InodeType {
		FILE, FOLDER
	};

	/**
	 * Attributes
	 */
	private final InodeType type;

	/**
	 * 
	 * @param type
	 */
	public FileOrFolder(InodeType type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(T file) {
		boolean out = false;
		if (file != null && file.getFile() != null && file.getFile().exists()) {
			out = (file.getFile().isDirectory() && type == InodeType.FOLDER) || (file.getFile().isFile() && type == InodeType.FILE);
		}
		return out;
	}

}
