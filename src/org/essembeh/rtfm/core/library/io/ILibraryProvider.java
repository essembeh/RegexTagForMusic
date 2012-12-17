package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.util.List;

import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.version.IReader;

public interface ILibraryProvider extends IReader {

	/**
	 * 
	 * @param virtualFile
	 * @return
	 */
	boolean fileExists(VirtualFile virtualFile);

	/**
	 * 
	 * @param virtualFile
	 * @return
	 */
	List<Attribute> getAttributesOfFile(VirtualFile virtualFile);

	/**
	 * 
	 * @return
	 */
	File getRootFolder();
}
