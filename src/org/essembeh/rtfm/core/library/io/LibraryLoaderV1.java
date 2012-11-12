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
import org.essembeh.rtfm.model.library.version1.TFile;
import org.essembeh.rtfm.model.library.version1.TLibraryV1;

import com.google.inject.Inject;

public class LibraryLoaderV1 extends JaxbObjectReader<Library, TLibraryV1> {
	/**
	 * Attributes
	 */
	private final static String RTFM_TAGGED = "rtfm:tagged";
	private final static Logger logger = Logger.getLogger(LibraryLoaderV1.class);

	/**
	 * 
	 */
	@Inject
	public LibraryLoaderV1() {
		super(TLibraryV1.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.JaxbObjectReader#readObjectFromModel(java.lang.Object, org.essembeh.rtfm.core.utils.version.ILoadable)
	 */
	@Override
	protected void readObjectFromModel(TLibraryV1 model, Library library) throws ReaderException {

		// Scan root folder
		try {
			library.scanFolder(new File(model.getPath()));
		} catch (IOException e) {
			throw new ReaderException(e.getMessage());
		}

		// Update files
		IdList<TFile, Identifier<TFile>> modelFiles = new IdList<TFile, Identifier<TFile>>(new Identifier<TFile>() {
			@Override
			public String getId(TFile o) {
				return o.getPath();
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

	protected void updateMusicFile(IXFile musicFile, TFile model) {
		Boolean isTagged = model.isTagged();
		if (isTagged != null) {
			logger.debug("Set file: " + model.getPath() + ", tagged: " + isTagged);
			Attribute attribute = new Attribute(RTFM_TAGGED, isTagged.toString());
			musicFile.getAttributeList().add(attribute);
		}
	}

}
