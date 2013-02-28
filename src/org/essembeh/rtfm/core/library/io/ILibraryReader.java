package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.essembeh.rtfm.core.utils.version.IReader;

public interface ILibraryReader extends IReader {

	/**
	 * Returns the root folder
	 * 
	 * @return
	 */
	File getRootFolder();

	/**
	 * Returns a list of virtual paths
	 * 
	 * @return
	 */
	List<String> getListOfFiles();

	/**
	 * 
	 * @param virtualPath
	 * @return
	 */
	Map<String, String> getAttributesForFile(String virtualPath);
}