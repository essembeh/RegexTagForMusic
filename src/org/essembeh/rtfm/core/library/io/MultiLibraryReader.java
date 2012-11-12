package org.essembeh.rtfm.core.library.io;

import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.utils.version.MultiObjectReader;

import com.google.inject.Inject;

public class MultiLibraryReader extends MultiObjectReader<Library> {

	/**
	 * 
	 */
	@Inject
	public MultiLibraryReader(LibraryLoaderV2 version2, LibraryLoaderV1 version1) {
		addreader(version2);
		addreader(version1);
	}
}
