package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.event.EventHandler;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

import com.google.inject.Inject;

public class GenericLibraryLoader implements LibraryLoader {

	private static Logger logger = Logger.getLogger(GenericLibraryLoader.class);
	List<LibraryLoader> libraryLoaders;
	LibraryLoader libraryLoader;
	EventHandler eventHandler;

	@Inject
	public GenericLibraryLoader(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
		this.libraryLoaders = new ArrayList<LibraryLoader>();
		this.libraryLoader = null;
		this.libraryLoaders.add(new LibraryLoaderV2(eventHandler));
		this.libraryLoaders.add(new LibraryLoaderV1(eventHandler));
	}

	@Override
	public boolean load(File source) {
		logger.debug("Trying to load file: " + source.getAbsolutePath());
		boolean rc = false;
		libraryLoader = null;
		for (LibraryLoader loader : libraryLoaders) {
			if (loader.load(source)) {
				this.libraryLoader = loader;
				logger.debug("Found loader: " + libraryLoader);
				rc = true;
				break;
			}
		}
		return rc;
	}

	@Override
	public File getRootFolder() throws LibraryException {
		if (libraryLoader == null) {
			throw new LibraryException("LibraryLoader is not initialized");
		}
		return libraryLoader.getRootFolder();
	}

	@Override
	public void update(IdList<MusicFile, Identifier<MusicFile>> listOfFiles) throws LibraryException {
		if (libraryLoader == null) {
			throw new LibraryException("LibraryLoader is not initialized");
		}
		libraryLoader.update(listOfFiles);
	}
}
