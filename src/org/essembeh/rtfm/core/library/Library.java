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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.configuration.MusicFileFactory;
import org.essembeh.rtfm.core.event.EventHandler;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.MusicFileIdentifier;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.filter.Filter;
import org.essembeh.rtfm.core.library.io.LibraryLoader;
import org.essembeh.rtfm.core.library.io.LibraryWriter;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.FileUtils;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Library {

	/**
	 * The logger.
	 */
	protected static Logger logger = Logger.getLogger(Library.class);

	LibraryLoader libraryLoader;
	LibraryWriter libraryWriter;
	MusicFileFactory musicFileFactory;
	RTFMProperties properties;
	EventHandler eventHandler;

	File rootFolder;
	IdList<MusicFile, Identifier<MusicFile>> listOfFiles;

	@Inject
	public Library(	MusicFileFactory musicFileFactory,
					@Named("rtfm.properties") RTFMProperties properties,
					LibraryLoader libraryLoader,
					LibraryWriter libraryWriter,
					EventHandler eventHandler) {
		this.musicFileFactory = musicFileFactory;
		this.properties = properties;
		this.libraryLoader = libraryLoader;
		this.libraryWriter = libraryWriter;
		this.eventHandler = eventHandler;
		clear();
	}

	protected void clear() {
		this.rootFolder = null;
		this.listOfFiles = new IdList<MusicFile, Identifier<MusicFile>>(new MusicFileIdentifier());
	}

	public IdList<MusicFile, Identifier<MusicFile>> getAllFiles() {
		return getFilteredFiles(null);
	}

	public IdList<MusicFile, Identifier<MusicFile>> getFilteredFiles(Filter filter) {
		IdList<MusicFile, Identifier<MusicFile>> list = listOfFiles.newEmptyOne();
		if (filter == null) {
			list.addAll(listOfFiles);
		} else {
			list.addAll(filter.filter(listOfFiles));
		}
		return list;
	}

	public File getRootFolder() {
		return this.rootFolder;
	}

	public void scanFolder(File folder) throws IOException {

		if (!folder.exists() || !folder.isDirectory()) {
			throw new IOException("The root folder is invalid: " + folder.getAbsolutePath());
		}

		// Clean the previous musicfiles
		clear();

		// Set root folder
		this.rootFolder = folder;

		// Search all files
		boolean scanHiddenFiles = properties.getBoolean("scan.hidden.files");
		List<File> allFiles = FileUtils.searchFilesInFolder(this.rootFolder, scanHiddenFiles);

		for (File file : allFiles) {
			logger.debug("Found: " + file.getAbsolutePath());
			VirtualFile virtualFile = new VirtualFile(file, folder);
			MusicFile musicFile;
			try {
				musicFile = musicFileFactory.createMusicFile(virtualFile);
				if (musicFile != null) {
					listOfFiles.add(musicFile);
				} else {
					eventHandler.noFileHandlerForFile(virtualFile);
				}
			} catch (InstantiationException e) {
				eventHandler.errorInstantiateFile(virtualFile, e.getMessage());
			} catch (DynamicAttributeException e) {
				eventHandler.errorMatchingDynamicAttribute(virtualFile, e.getMessage());
			}
		}
		// Sort the list
		logger.info("Found " + this.listOfFiles.size() + " files in folder: " + this.rootFolder.getAbsolutePath());
	}

	public void loadFrom(File source) throws LibraryException, IOException {
		if (libraryLoader.load(source)) {
			scanFolder(libraryLoader.getRootFolder());
			libraryLoader.update(listOfFiles);
		} else {
			logger.error("Error using library loader for file: " + source.getAbsolutePath());
			clear();
			throw new LibraryException("Cannot load library: " + source.getAbsolutePath());
		}
	}

	public void writeTo(File destination) throws LibraryException {
		this.libraryWriter.write(destination, listOfFiles, rootFolder);
	}

	@Override
	public String toString() {
		return "Library [rootFolder=" + rootFolder + ", listOfFiles={" + StringUtils.join(listOfFiles, ", ")
				+ "}, libraryLoader=" + libraryLoader + ", libraryWriter=" + libraryWriter + ", configuration="
				+ musicFileFactory + ", properties=" + properties + "]";
	}

}
