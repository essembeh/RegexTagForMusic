package org.essembeh.rtfm.core.library.io;

import java.io.File;

import org.essembeh.rtfm.core.exception.LibraryException;

public interface ILibraryWriter {
	void writeLibrary(File destination, LibraryWriterCallback callback) throws LibraryException;
}
