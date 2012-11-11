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
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.ILibrary;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.model.library.version2.TAttribute;
import org.essembeh.rtfm.model.library.version2.TFile;
import org.essembeh.rtfm.model.library.version2.TLibraryV2;

import com.google.inject.Inject;

public class LibraryLoaderV2 implements ILibraryLoader {
	/**
	 * Attributes
	 */
	private final static Logger logger = Logger.getLogger(LibraryLoaderV2.class);

	/**
	 * Constructor
	 */
	@Inject
	public LibraryLoaderV2() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryLoader#loadLibrary(java.io.File)
	 */
	@Override
	public void loadLibrary(InputStream source, ILibrary library) throws LibraryException, IOException {
		TLibraryV2 model = null;
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.library.version2");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
			JAXBElement<TLibraryV2> root = unmarshaller.unmarshal(new StreamSource(source), TLibraryV2.class);
			model = root.getValue();
		} catch (JAXBException e) {
			logger.info("Cannot load Library version 2 from source");
			throw new LibraryException(e);
		}

		// Scan folder
		File rootFolder = new File(model.getRootFolder().getPath());
		library.scanFolder(rootFolder);

		// Update files
		IdList<TFile, Identifier<TFile>> modelFiles = new IdList<TFile, Identifier<TFile>>(new Identifier<TFile>() {
			@Override
			public String getId(TFile o) {
				return o.getVirtualpath();
			}
		}, model.getFile());

		for (IMusicFile musicFile : library.getAllFiles()) {
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
	private void updateMusicFile(IMusicFile musicFile, TFile model) {
		for (TAttribute attribute : model.getAttribute()) {
			String name = attribute.getName();
			String value = attribute.getValue();
			if (musicFile.getAttributeList().containsKey(name)) {
				// Update
				musicFile.getAttributeList().get(name).setValue(value);
			} else {
				// Create
				musicFile.getAttributeList().add(new Attribute(name, value, false));
			}
		}
	}
}
