package org.essembeh.rtfm.core.library.io;

import java.io.File;

import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public interface LibraryWriter {
	void write(File destination, IdList<MusicFile, Identifier<MusicFile>> library, File rootFolder)
			throws LibraryException;
}
