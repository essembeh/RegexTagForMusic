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
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.configuration.MusicFileService;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.io.GenericLibraryIO;
import org.essembeh.rtfm.core.library.io.LibraryLoaderCallback;
import org.essembeh.rtfm.core.library.io.LibraryWriterCallback;
import org.essembeh.rtfm.core.library.listener.ILibraryListener;
import org.essembeh.rtfm.core.library.listener.LibraryListenerContainer;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.FileUtils;
import org.essembeh.rtfm.core.utils.identifiers.MusicFileIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.core.utils.listener.IListenable;

import com.google.inject.Inject;

public class Library implements IListenable<ILibraryListener> {

	/**
	 * The logger.
	 */
	protected static Logger logger = Logger.getLogger(Library.class);

	private final RTFMProperties properties;
	private final MusicFileService musicFileService;
	private final GenericLibraryIO genericLibraryIO;
	private final LibraryListenerContainer listeners;

	private volatile File rootFolder;
	private volatile IdList<IMusicFile, Identifier<IMusicFile>> listOfFiles;

	@Inject
	public Library(RTFMProperties properties, MusicFileService musicFileService, GenericLibraryIO genericLibraryIO) {
		this.musicFileService = musicFileService;
		this.properties = properties;
		this.genericLibraryIO = genericLibraryIO;
		this.listeners = new LibraryListenerContainer();
		clear();
	}

	protected void clear() {
		this.rootFolder = null;
		this.listOfFiles = new IdList<IMusicFile, Identifier<IMusicFile>>(new MusicFileIdentifier());
	}

	public List<IMusicFile> getAllFiles() {
		return listOfFiles.toList();
	}

	public File getRootFolder() {
		return this.rootFolder;
	}

	public void scanFolder(File folder) throws IOException {

		if (!folder.exists() || !folder.isDirectory()) {
			listeners.scanFolderFailed(folder);
			throw new IOException("The root folder is invalid: " + folder.getAbsolutePath());
		}

		// Clean the previous musicfiles
		clear();

		// Set root folder
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
				musicFile = musicFileService.createMusicFile(virtualFile);
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

	public void loadFrom(File source) throws LibraryException, IOException {
		clear();
		final IdList<IMusicFile, Identifier<IMusicFile>> oldFiles = listOfFiles.newEmptyOne();
		try {
			genericLibraryIO.loadLibrary(source, new LibraryLoaderCallback() {
				@Override
				public void setRootFolder(File rootFolder) throws IOException {
					scanFolder(rootFolder);
				}

				@Override
				public IMusicFile getExistingMusicFile(String virtualPath, FileType type) {
					IMusicFile musicFile = null;
					if (listOfFiles.containsKey(virtualPath)) {
						musicFile = listOfFiles.get(virtualPath);
						logger.debug("Update file: " + musicFile);
					} else {
						logger.debug("Removed file during importation: " + virtualPath);
						listeners.loadLibraryFileRemoved(virtualPath, type);
					}
					oldFiles.add(musicFile);

					return listOfFiles.get(virtualPath);
				}
			});
		} catch (IOException e) {
			listeners.loadLibraryFailed(source);
			throw e;
		} catch (LibraryException e) {
			listeners.loadLibraryFailed(source);
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

	public void writeTo(File destination) throws LibraryException {
		genericLibraryIO.writeLibrary(destination, new LibraryWriterCallback() {
			@Override
			public File getRootFolder() {
				return rootFolder;
			}

			@Override
			public List<IMusicFile> getMusicFiles() {
				return listOfFiles.toList();
			}
		});
	}

	@Override
	public String toString() {
		return "Library [rootFolder=" + rootFolder + ", files: " + listOfFiles.size() + "]";
	}

	@Override
	public void addListener(ILibraryListener listener) {
		listeners.addListener(listener);
	}

	@Override
	public void removeListener(ILibraryListener listener) {
		listeners.removeListener(listener);
	}

	@Override
	public void removeAllListener() {
		listeners.removeAllListener();
	}
}
