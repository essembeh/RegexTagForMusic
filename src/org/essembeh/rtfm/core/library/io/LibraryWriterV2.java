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
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.ILibrary;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.model.library.version2.ObjectFactory;
import org.essembeh.rtfm.model.library.version2.TAttribute;
import org.essembeh.rtfm.model.library.version2.TFile;
import org.essembeh.rtfm.model.library.version2.TLibraryV2;
import org.essembeh.rtfm.model.library.version2.TRootFolder;

import com.google.inject.Inject;

public class LibraryWriterV2 implements ILibraryWriter {
	/**
	 * Attributes
	 */
	private final ObjectFactory objectFactory;
	private final String exportableAttribute;

	/**
	 * 
	 * @param properties
	 */
	@Inject
	public LibraryWriterV2(RTFMProperties properties) {
		this.exportableAttribute = properties.getProperty("library.musicfile.attribute.export");
		this.objectFactory = new ObjectFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.library.io.ILibraryWriter#writeLibrary(java.io.OutputStream, org.essembeh.rtfm.core.library.Library)
	 */
	@Override
	public void writeLibrary(OutputStream destination, ILibrary library) throws LibraryException {
		TLibraryV2 model = toModel(library);
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.library.version2");
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			JAXBElement<TLibraryV2> root = objectFactory.createLibrary(model);
			marshaller.marshal(root, destination);
		} catch (JAXBException e) {
			throw new LibraryException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param musicFile
	 * @return
	 */
	private boolean isExportable(IMusicFile musicFile) {
		boolean isExportable = true;
		if (exportableAttribute != null) {
			Attribute exportAttribute = musicFile.getAttributeList().get(exportableAttribute);
			if (Boolean.FALSE.toString().equalsIgnoreCase(exportAttribute.getValue())) {
				isExportable = false;
			}
		}
		return isExportable;
	}

	/**
	 * 
	 * @param library
	 * @return
	 */
	private TLibraryV2 toModel(ILibrary library) {
		TLibraryV2 model = objectFactory.createTLibraryV2();
		model.setRootFolder(toModel(library.getRootFolder()));
		for (IMusicFile musicFile : library.getAllFiles()) {
			if (isExportable(musicFile)) {
				model.getFile().add(toModel(musicFile));
			}
		}
		return model;
	}

	/**
	 * 
	 * @param rootFolder
	 * @return
	 */
	protected TRootFolder toModel(File rootFolder) {
		TRootFolder model = objectFactory.createTRootFolder();
		model.setPath(rootFolder.getAbsolutePath());
		return model;
	}

	/**
	 * 
	 * @param musicFile
	 * @return
	 */
	protected TFile toModel(IMusicFile musicFile) {
		TFile model = objectFactory.createTFile();
		model.setType(musicFile.getType().toString());
		model.setVirtualpath(musicFile.getVirtualPath());
		List<Attribute> attributeList = musicFile.getAttributeList().toList();
		Collections.sort(attributeList);
		for (Attribute a : attributeList) {
			if (!a.isHidden()) {
				model.getAttribute().add(toModel(a));
			}
		}
		return model;
	}

	/**
	 * 
	 * @param attribute
	 * @return
	 */
	protected TAttribute toModel(Attribute attribute) {
		TAttribute model = objectFactory.createTAttribute();
		model.setName(attribute.getName());
		model.setValue(attribute.getValue());
		return model;
	}
}
