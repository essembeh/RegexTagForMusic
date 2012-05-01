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
