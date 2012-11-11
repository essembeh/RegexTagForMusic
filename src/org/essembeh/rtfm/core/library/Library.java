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

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.configuration.CoreConfiguration;
import org.essembeh.rtfm.core.configuration.IExecutionEnvironment;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.io.GenericLibraryIO;
import org.essembeh.rtfm.core.library.listener.ILibraryListener;
import org.essembeh.rtfm.core.library.listener.LibraryListenerContainer;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.FileUtils;
import org.essembeh.rtfm.core.utils.identifiers.MusicFileIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.core.utils.listener.IListenable;

import com.google.inject.Inject;

public class Library implements IListenable<ILibraryListener>, ILibrary {
	/**
	 * Attributes
	 */
	private final static Logger logger = Logger.getLogger(Library.class);
	private final RTFMProperties properties;
	private final CoreConfiguration coreConfiguration;
	private final GenericLibraryIO libraryIO;
	private final IdList<IMusicFile, Identifier<IMusicFile>> listOfFiles;
	private final LibraryListenerContainer listeners;
	private volatile File rootFolder;

	/**
	 * 
	 * @param libraryIO
	 * @param properties
	 * @param coreConfiguration
	 */
	@Inject
	public Library(GenericLibraryIO libraryIO, RTFMProperties properties, CoreConfiguration coreConfiguration) {
		this.coreConfiguration = coreConfiguration;
		this.properties = properties;
		this.libraryIO = libraryIO;
		this.listeners = new LibraryListenerContainer();
		this.listOfFiles = new IdList<IMusicFile, Identifier<IMusicFile>>(new MusicFileIdentifier());
		clear();
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
	public List<IMusicFile> getAllFiles() {
		return listOfFiles.toList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.ILibrary#getExecutionEnvironment()
	 */
	@Override
	public IExecutionEnvironment getExecutionEnvironment() {
		return coreConfiguration;
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

	/**
	 * 
	 * @param configuration
	 * @throws ConfigurationException
	 */
	public void loadConfiguration(InputStream configuration) throws ConfigurationException {
		clear();
		coreConfiguration.load(configuration);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.ILibrary#loadFrom(java.io.InputStream)
	 */
	@Override
	public void loadFrom(InputStream source) throws LibraryException, IOException {
		clear();
		final IdList<IMusicFile, Identifier<IMusicFile>> oldFiles = listOfFiles.newEmptyOne();
		try {
			libraryIO.loadLibrary(source, this);
		} catch (LibraryException e) {
			listeners.loadLibraryFailed();
			throw e;
		}
		// Detect new files
		for (IMusicFile iMusicFile : listOfFiles) {
			if (!oldFiles.contains(iMusicFile)) {
				logger.debug("New file during importation: " + iMusicFile);
				listeners.loadLibraryNewFile(iMusicFile);
			}
		}
		logger.info("File count: " + listOfFiles.size());
		logger.info("New file: " + (listOfFiles.size() - oldFiles.size()));
		listeners.loadLibrarySucceeeded();
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

		if (!folder.exists() || !folder.isDirectory()) {
			listeners.scanFolderFailed(folder);
			throw new IOException("The root folder is invalid: " + folder.getAbsolutePath());
		}

		// Clean the previous musicfiles
		clear();
		this.rootFolder = folder;

		// Search all files
		boolean scanHiddenFiles = properties.getBoolean("scan.hidden.files");
		String ignoreAttribute = properties.getProperty("library.musicfile.attribute.ignore");
		List<File> allFiles = FileUtils.searchFilesInFolder(this.rootFolder, scanHiddenFiles);

		for (File file : allFiles) {
			logger.debug("Found: " + file.getAbsolutePath());
			VirtualFile virtualFile = new VirtualFile(file, folder);
			IMusicFile musicFile;
			try {
				musicFile = coreConfiguration.createMusicFile(virtualFile);
				if (musicFile != null) {
					if (ignoreAttribute != null && musicFile.getAttributeList().containsKey(ignoreAttribute)) {
						logger.info("Ignored file: " + musicFile);
					} else {
						listOfFiles.add(musicFile);
					}
				} else {
					listeners.noFileHandlerForFile(virtualFile);
				}
			} catch (DynamicAttributeException e) {
				listeners.errorMatchingDynamicAttribute(virtualFile, e.getMessage());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.ILibrary#writeTo(java.io.OutputStream)
	 */
	@Override
	public void writeTo(OutputStream destination) throws LibraryException {
		libraryIO.writeLibrary(destination, this);
	}

	/**
	 * Resets root folder and file list.
	 */
	private void clear() {
		this.rootFolder = null;
		listOfFiles.clear();
	}
}
