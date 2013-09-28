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
package org.essembeh.rtfm.fs.condition.impl;

import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IFile;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class FileOrFolder implements ICondition {
	public enum InodeType {
		FILE, FOLDER
	};

	private final InodeType type;

	public FileOrFolder(InodeType type) {
		this.type = type;
	}

	@Override
	public boolean isTrue(IResource resource) {
		return (type == InodeType.FILE && resource instanceof IFile) || (type == InodeType.FOLDER && resource instanceof IFolder);
	}

}
