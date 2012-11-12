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
package org.essembeh.rtfm.core.library.listener;

import java.io.File;

import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public class DefaultLibraryListener implements ILibraryListener {

	@Override
	public void errorMatchingDynamicAttribute(VirtualFile file, String comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public void noFileHandlerForFile(VirtualFile file) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadLibraryNewFile(IXFile musicFile) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadLibraryFileRemoved(String virtualPath, FileType fileType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadLibrarySucceeeded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadLibraryFailed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void scanFolderSucceeeded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void scanFolderFailed(File folder) {
		// TODO Auto-generated method stub

	}


}
