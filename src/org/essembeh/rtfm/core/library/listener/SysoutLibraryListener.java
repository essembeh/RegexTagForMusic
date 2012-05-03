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

import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public class SysoutLibraryListener implements ILibraryListener {

	@Override
	public void errorMatchingDynamicAttribute(VirtualFile file, String comment) {
		System.out.println("Event> Error with dynamic attribute, file:" + file + ", message:" + comment);

	}

	@Override
	public void noFileHandlerForFile(VirtualFile file) {
		System.out.println("Event> No file handler found for file:" + file);
	}

	@Override
	public void loadLibraryNewFile(IMusicFile musicFile) {
		System.out.println("Event> New file in Library: " + musicFile);
	}

	@Override
	public void loadLibraryFileRemoved(String virtualpath, FileType fileType) {
		System.out.println("Event> Old file no more present in Library, type:" + fileType + ", path:" + virtualpath);
	}

}
