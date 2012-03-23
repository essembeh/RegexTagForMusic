package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.io.IOException;

import org.essembeh.rtfm.core.exception.LibraryException;

public interface ILibraryLoader {

	void loadLibrary(File source, LibraryLoaderCallback callback) throws LibraryException, IOException;
}
