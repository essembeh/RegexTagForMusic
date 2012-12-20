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

import java.io.File;

import org.essembeh.rtfm.core.utils.FileUtils;

public class VirtualFile extends File implements IVirtualFile {
	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 2859877446763137741L;
	private final String virtualPath;

	/**
	 * 
	 * @param file
	 * @param parent
	 */
	public VirtualFile(File file, File parent) {
		super(file.getAbsolutePath());
		this.virtualPath = FileUtils.extractRelativePath(file, parent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.file.IVirtualFile#getVirtualPath()
	 */
	@Override
	public String getVirtualPath() {
		return virtualPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.File#toString()
	 */
	@Override
	public String toString() {
		return virtualPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.file.IVirtualFile#getFile()
	 */
	@Override
	public File getFile() {
		return this;
	}

}
