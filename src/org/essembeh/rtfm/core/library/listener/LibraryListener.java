package org.essembeh.rtfm.core.library.listener;

import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public interface LibraryListener {

	void errorMatchingDynamicAttribute(VirtualFile file, String comment);

	void noFileHandlerForFile(VirtualFile file);

	void loadLibraryNewFile(IMusicFile musicFile);

	void loadLibraryFileRemoved(String virtualPath, FileType fileType);

}
