package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.util.List;

import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.version.MultiReader;

import com.google.inject.Inject;

public class MultiLibraryReader extends MultiReader<ILibraryProvider> implements ILibraryProvider {

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
	 * @see org.essembeh.rtfm.core.utils.version2.ILibraryLoader#fileExists(org.essembeh.rtfm.core.library.file.VirtualFile)
	 */
	@Override
	public boolean fileExists(VirtualFile virtualFile) {
		return getLastReader().fileExists(virtualFile);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version2.ILibraryLoader#getAttributesOfFile(org.essembeh.rtfm.core.library.file.VirtualFile)
	 */
	@Override
	public List<Attribute> getAttributesOfFile(VirtualFile virtualFile) {
		return getLastReader().getAttributesOfFile(virtualFile);
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

}
