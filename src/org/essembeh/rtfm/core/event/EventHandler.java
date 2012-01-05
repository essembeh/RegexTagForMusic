package org.essembeh.rtfm.core.event;

import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public interface EventHandler {

	void errorInstantiateFile(VirtualFile file, String comment);

	void errorMatchingDynamicAttribute(VirtualFile file, String comment);

	void noFileHandlerForFile(VirtualFile file);

	void loadLibraryNewFile(MusicFile musicFile);

	void loadLibraryOldFile(String type, String virtualpath);

}
