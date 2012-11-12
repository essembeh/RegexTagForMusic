package org.essembeh.rtfm.core.library;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.essembeh.rtfm.core.library.file.IXFile;

public interface ILibrary {

	/**
	 * 
	 * @return
	 */
	List<IXFile> getAllFiles();

	/**
	 * 
	 * @return
	 */
	File getRootFolder();

	/**
	 * 
	 * @param folder
	 * @throws IOException
	 */
	void scanFolder(File folder) throws IOException;

}