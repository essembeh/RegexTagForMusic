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

package org.essembeh.rtfm.core.library;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.filehandler.FileHandlerManager;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.io.ILibraryReader;
import org.essembeh.rtfm.core.library.listener.ILibraryListener;
import org.essembeh.rtfm.core.library.listener.LibraryListenerContainer;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.FileUtils;
import org.essembeh.rtfm.core.utils.identifiers.MusicFileIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.core.utils.listener.IListenable;
import org.essembeh.rtfm.core.utils.version.ILoadable;
import org.essembeh.rtfm.core.utils.version.IObjectWriter;
import org.essembeh.rtfm.core.utils.version.ISaveable;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;
import org.essembeh.rtfm.core.utils.version.exceptions.WriterException;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Library implements IListenable<ILibraryListener>, ILibrary, ILoadable, ISaveable {
	/**
	 * Attributes
	 */
	private final static Logger logger = Logger.getLogger(Library.class);
	private final RTFMProperties properties;
	private final FileHandlerManager fileHandlerManager;
	private final ILibraryReader libraryReader;
	private final IObjectWriter<Library> libraryWriter;
	private final IdList<IXFile, Identifier<IXFile>> listOfFiles;
	private final LibraryListenerContainer listeners;
	private volatile File rootFolder;

	/**
	 * 
	 * @param libraryReader
	 * @param libraryWriter
	 * @param properties
	 * @param fileService
	 */
	@Inject
	public Library(@Named("LibraryReader") ILibraryReader libraryReader, @Named("LibraryWriter") IObjectWriter<Library> libraryWriter,
			RTFMProperties properties, FileHandlerManager fileHandlerManager) {
		this.libraryReader = libraryReader;
		this.libraryWriter = libraryWriter;
		this.fileHandlerManager = fileHandlerManager;
		this.properties = properties;
		this.listeners = new LibraryListenerContainer();
		this.listOfFiles = new IdList<IXFile, Identifier<IXFile>>(new MusicFileIdentifier());
		this.rootFolder = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#addListener(java.lang.Object)
	 */
	@Override
	public void addListener(ILibraryListener listener) {
		listeners.addListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.ILibrary#getAllFiles()
	 */
	@Override
	public List<IXFile> getAllFiles() {
		return listOfFiles.toList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.ILibrary#getRootFolder()
	 */
	@Override
	public File getRootFolder() {
		return this.rootFolder;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#removeAllListener()
	 */
	@Override
	public void removeAllListener() {
		listeners.removeAllListener();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.listener.IListenable#removeListener(java.lang.Object)
	 */
	@Override
	public void removeListener(ILibraryListener listener) {
		listeners.removeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.ILibrary#scanFolder(java.io.File)
	 */
	@Override
	public void scanFolder(File folder) throws IOException {
		internalScanFolder(folder);
	}

	/**
	 * 
	 * @param folder
	 * @throws IOException
	 */
	public void internalScanFolder(File folder) throws IOException {
		resetValues();
		// Check if valid folder
		if (folder == null || !folder.exists() || !folder.isDirectory()) {
			throw new IOException("The root folder is invalid: " + folder.getAbsolutePath());
		}

		// Save root folder
		this.rootFolder = folder;

		// Search all files
		boolean scanHiddenFiles = properties.getBoolean("scan.hidden.files");
		List<File> allFiles = FileUtils.searchFilesInFolder(this.rootFolder, scanHiddenFiles);

		for (File file : allFiles) {
			logger.debug("Found: " + file.getAbsolutePath());
			VirtualFile virtualFile = new VirtualFile(file, folder);
			IXFile xFile = fileHandlerManager.createXFile(virtualFile);
			if (xFile != null) {
				listOfFiles.add(xFile);
			} else {
				logger.warn("No filehandler for file: " + virtualFile);
			}
		}
		logger.info("Found " + this.listOfFiles.size() + " files in folder: " + this.rootFolder.getAbsolutePath());
		listeners.scanFolderSucceeeded();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Library [rootFolder=" + rootFolder + ", files: " + listOfFiles.size() + "]";
	}

	/**
	 * 
	 * 
	 */
	public void resetValues() {
		this.listOfFiles.clear();
		this.rootFolder = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.ILoadable#load(java.io.InputStream)
	 */
	@Override
	public void load(InputStream inputStream) throws ReaderException {
		resetValues();
		libraryReader.read(inputStream);
		try {
			internalScanFolder(libraryReader.getRootFolder());
			
			// Arrange previous files
			List<String> previousFiles = libraryReader.getListOfFiles();
			for (IXFile file : listOfFiles) {
				// test if previous file exists
				if (previousFiles.contains(file.getVirtualPath())) {
					// Get attibutes
					Map<String, String> attributes = libraryReader.getAttributesForFile(file.getVirtualPath());
					for (Entry<String, String> e : attributes.entrySet()) {
						file.getAttributes().updateIfExists(e.getKey(), e.getValue());
					}
				}
			}
		} catch (IOException e) {
			throw new ReaderException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.ISaveable#save(java.io.OutputStream)
	 */
	@Override
	public void save(OutputStream outputStream) throws WriterException {
		libraryWriter.writeObject(outputStream, this);
	}
}
