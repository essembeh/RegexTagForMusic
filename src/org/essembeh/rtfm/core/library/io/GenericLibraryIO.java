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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.ILibrary;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class GenericLibraryIO implements ILibraryLoader, ILibraryWriter {

	/**
	 * Attributes
	 */
	private final static Logger logger = Logger.getLogger(GenericLibraryIO.class);
	private final List<ILibraryLoader> libraryLoaders;
	private final ILibraryWriter libraryWriter;

	/**
	 * 
	 * @param libraryWriter
	 * @param libraryLoaderV1
	 * @param libraryLoaderV2
	 */
	@Inject
	public GenericLibraryIO(ILibraryWriter libraryWriter, @Named("LibraryLoaderV1") ILibraryLoader libraryLoaderV1,
			@Named("LibraryLoaderV2") ILibraryLoader libraryLoaderV2) {
		this.libraryLoaders = new ArrayList<ILibraryLoader>();
		this.libraryLoaders.add(libraryLoaderV2);
		this.libraryLoaders.add(libraryLoaderV1);
		this.libraryWriter = libraryWriter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryWriter#writeLibrary(java.io.OutputStream, org.essembeh.rtfm.core.library.Library)
	 */
	@Override
	public void writeLibrary(OutputStream destination, ILibrary library) throws LibraryException {
		libraryWriter.writeLibrary(destination, library);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryLoader#loadLibrary(java.io.InputStream, org.essembeh.rtfm.core.library.Library)
	 */
	@Override
	public void loadLibrary(InputStream source, ILibrary library) throws LibraryException, IOException {
		for (ILibraryLoader libraryLoader : libraryLoaders) {
			try {
				libraryLoader.loadLibrary(source, library);
				return;
			} catch (LibraryException e) {
				logger.info("Invalid library loader: " + libraryLoader);
			}
		}
		logger.error("Cannot find a valid library loader");
		throw new LibraryException("Cannot find a valid library loader");
	}
}
