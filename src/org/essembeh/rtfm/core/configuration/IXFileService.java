package org.essembeh.rtfm.core.configuration;

import java.util.List;

import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public interface IXFileService {
	/**
	 * 
	 * @return
	 */
	List<FileType> getDeclaredFileTypes();

	/**
	 * 
	 * @param file
	 * @return
	 */
	IXFile createMusicFile(VirtualFile file);
}
