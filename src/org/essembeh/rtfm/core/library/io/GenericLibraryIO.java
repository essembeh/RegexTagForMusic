package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.LibraryException;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class GenericLibraryIO implements ILibraryLoader, ILibraryWriter {

	private final static Logger logger = Logger.getLogger(GenericLibraryIO.class);
	private final List<ILibraryLoader> libraryLoaders;
	private final ILibraryWriter libraryWriter;

	@Inject
	public GenericLibraryIO(ILibraryWriter libraryWriter,
							@Named("LibraryLoaderV1") ILibraryLoader libraryLoaderV1,
							@Named("LibraryLoaderV2") ILibraryLoader libraryLoaderV2) {
		this.libraryLoaders = new ArrayList<ILibraryLoader>();
		this.libraryLoaders.add(libraryLoaderV2);
		this.libraryLoaders.add(libraryLoaderV1);
		this.libraryWriter = libraryWriter;
	}

	@Override
	public void writeLibrary(File destination, LibraryWriterCallback callback) throws LibraryException {
		libraryWriter.writeLibrary(destination, callback);
	}

	@Override
	public void loadLibrary(File source, LibraryLoaderCallback callback) throws LibraryException, IOException {
		for (ILibraryLoader libraryLoader : libraryLoaders) {
			try {
				libraryLoader.loadLibrary(source, callback);
				return;
			} catch (LibraryException e) {
				logger.info("Invalid library loader: " + libraryLoader);
			}
		}
		logger.error("Cannot find a valid library loader");
		throw new LibraryException("Cannot find a valid library loader");
	}
}
