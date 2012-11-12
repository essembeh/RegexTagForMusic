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

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.core.utils.version.JaxbObjectReader;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;
import org.essembeh.rtfm.model.library.version2.TAttribute;
import org.essembeh.rtfm.model.library.version2.TFile;
import org.essembeh.rtfm.model.library.version2.TLibraryV2;

import com.google.inject.Inject;

public class LibraryLoaderV2 extends JaxbObjectReader<Library, TLibraryV2> {
	/**
	 * Attributes
	 */
	private final static Logger logger = Logger.getLogger(LibraryLoaderV2.class);

	/**
	 * Constructor
	 */
	@Inject
	public LibraryLoaderV2() {
		super(TLibraryV2.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.JaxbObjectReader#readObjectFromModel(java.lang.Object, org.essembeh.rtfm.core.utils.version.ILoadable)
	 */
	@Override
	protected void readObjectFromModel(TLibraryV2 model, Library library) throws ReaderException {

		// Scan folder
		File rootFolder = new File(model.getRootFolder().getPath());
		try {
			library.scanFolder(rootFolder);
		} catch (IOException e) {
			throw new ReaderException(e.getMessage());
		}

		// Update files
		IdList<TFile, Identifier<TFile>> modelFiles = new IdList<TFile, Identifier<TFile>>(new Identifier<TFile>() {
			@Override
			public String getId(TFile o) {
				return o.getVirtualpath();
			}
		}, model.getFile());

		for (IXFile musicFile : library.getAllFiles()) {
			TFile modelFile = modelFiles.get(musicFile.getVirtualPath());
			if (modelFile == null) {
				// New file
				logger.info("Cannot find file in loaded library: " + musicFile);
			} else {
				updateMusicFile(musicFile, modelFile);
				logger.debug("Updated file: " + musicFile);
			}
		}
	}

	/**
	 * 
	 * @param musicFile
	 * @param model
	 */
	private void updateMusicFile(IXFile musicFile, TFile model) {
		for (TAttribute attribute : model.getAttribute()) {
			String name = attribute.getName();
			String value = attribute.getValue();
			if (musicFile.getAttributeList().containsKey(name)) {
				// Update
				musicFile.getAttributeList().get(name).setValue(value);
			} else {
				// Create
				musicFile.getAttributeList().add(new Attribute(name, value));
			}
		}
	}
}
