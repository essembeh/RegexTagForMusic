package org.essembeh.rtfm.core.configuration;

import java.util.List;

import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.io.ILibraryProvider;

public interface IXFileService {
	/**
	 * 
	 * @return
	 */
	List<FileType> getDeclaredFileTypes();

	/**
	 * 
	 * @param file
	 * @param provider
	 * @return
	 */
	IXFile createXFile(VirtualFile file, ILibraryProvider provider);
}
