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
import org.essembeh.rtfm.core.utils.listener.ListenerContainer;

public class LibraryListenerContainer extends ListenerContainer<ILibraryListener> implements ILibraryListener {

	@Override
	public void errorMatchingDynamicAttribute(final VirtualFile file, final String comment) {
		forEachListener(new ListenerAction<ILibraryListener>() {
			@Override
			public void execute(ILibraryListener listener) {
				listener.errorMatchingDynamicAttribute(file, comment);
			}
		});
	}

	@Override
	public void noFileHandlerForFile(final VirtualFile file) {
		forEachListener(new ListenerAction<ILibraryListener>() {
			@Override
			public void execute(ILibraryListener listener) {
				listener.noFileHandlerForFile(file);
			}
		});
	}

	@Override
	public void loadLibraryNewFile(final IMusicFile musicFile) {
		forEachListener(new ListenerAction<ILibraryListener>() {
			@Override
			public void execute(ILibraryListener listener) {
				listener.loadLibraryNewFile(musicFile);
			}
		});
	}

	@Override
	public void loadLibraryFileRemoved(final String virtualPath, final FileType type) {
		forEachListener(new ListenerAction<ILibraryListener>() {
			@Override
			public void execute(ILibraryListener listener) {
				listener.loadLibraryFileRemoved(virtualPath, type);
			}
		});
	}

}
