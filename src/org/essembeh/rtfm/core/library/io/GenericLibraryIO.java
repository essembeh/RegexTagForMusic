/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
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
