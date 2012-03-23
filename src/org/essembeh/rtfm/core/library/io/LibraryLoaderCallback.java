package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.io.IOException;

import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public interface LibraryLoaderCallback {

	void setRootFolder(File rootFolder) throws IOException;

	IMusicFile getExistingMusicFile(String virtualPath, FileType type);

}
