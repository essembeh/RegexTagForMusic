package org.essembeh.rtfm.core.library.io;

import java.io.File;

import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public interface LibraryLoader {

	boolean load(File source);

	File getRootFolder() throws LibraryException;

	void update(IdList<MusicFile, Identifier<MusicFile>> listOfFiles) throws LibraryException;

}
