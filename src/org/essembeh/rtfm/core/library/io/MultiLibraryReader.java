package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.essembeh.rtfm.core.utils.version.MultiReader;

import com.google.inject.Inject;

public class MultiLibraryReader extends MultiReader<ILibraryReader> implements ILibraryReader {

	/**
	 * 
	 */
	@Inject
	public MultiLibraryReader(LibraryReaderV2 version2, LibraryReaderV1 version1) {
		addReader(version2);
		addReader(version1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryProvider#getRootFolder()
	 */
	@Override
	public File getRootFolder() {
		return getLastReader().getRootFolder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryReader#getListOfFiles()
	 */
	@Override
	public List<String> getListOfFiles() {
		return getLastReader().getListOfFiles();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryReader#getAttributesForFile(java.lang.String)
	 */
	@Override
	public Map<String, String> getAttributesForFile(String virtualPath) {
		return getLastReader().getAttributesForFile(virtualPath);
	}

}
