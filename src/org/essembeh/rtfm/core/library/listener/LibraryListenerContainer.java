package org.essembeh.rtfm.core.library.listener;

import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.utils.AbstractListenerContainer;

public class LibraryListenerContainer extends AbstractListenerContainer<LibraryListener> implements LibraryListener {

	@Override
	public void errorMatchingDynamicAttribute(VirtualFile file, String comment) {
		for (LibraryListener l : this) {
			l.errorMatchingDynamicAttribute(file, comment);
		}
	}

	@Override
	public void noFileHandlerForFile(VirtualFile file) {
		for (LibraryListener l : this) {
			l.noFileHandlerForFile(file);
		}
	}

	@Override
	public void loadLibraryNewFile(IMusicFile musicFile) {
		for (LibraryListener l : this) {
			l.loadLibraryNewFile(musicFile);
		}
	}

	@Override
	public void loadLibraryFileRemoved(String virtualPath, FileType type) {
		for (LibraryListener l : this) {
			l.loadLibraryFileRemoved(virtualPath, type);
		}
	}

}
