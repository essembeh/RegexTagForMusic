package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.util.List;

import org.essembeh.rtfm.core.library.file.IMusicFile;

public interface LibraryWriterCallback {

	File getRootFolder();

	List<IMusicFile> getMusicFiles();
}
