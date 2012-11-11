package org.essembeh.rtfm.core.library;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.essembeh.rtfm.core.configuration.IExecutionEnvironment;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public interface ILibrary {

	/**
	 * 
	 * @return
	 */
	List<IMusicFile> getAllFiles();

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

	/**
	 * 
	 * @param source
	 * @throws LibraryException
	 * @throws IOException
	 */
	void loadFrom(InputStream source) throws LibraryException, IOException;

	/**
	 * 
	 * @param destination
	 * @throws LibraryException
	 */
	void writeTo(OutputStream destination) throws LibraryException;

	/**
	 * 
	 * @return
	 */
	IExecutionEnvironment getExecutionEnvironment();

}