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

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.core.library.ILibrary;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.Attributes;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.properties.SpecialAttribute;
import org.essembeh.rtfm.core.utils.jaxb.JaxbUtils;
import org.essembeh.rtfm.core.utils.version.IObjectWriter;
import org.essembeh.rtfm.core.utils.version.exceptions.WriterException;
import org.essembeh.rtfm.model.library.version2.ObjectFactory;
import org.essembeh.rtfm.model.library.version2.TAttribute;
import org.essembeh.rtfm.model.library.version2.TFile;
import org.essembeh.rtfm.model.library.version2.TLibraryV2;
import org.essembeh.rtfm.model.library.version2.TRootFolder;

import com.google.inject.Inject;

public class LibraryWriterV2 implements IObjectWriter<Library> {
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
		this.exportableAttribute = properties.getSpecialAttribute(SpecialAttribute.DB_EXPORT);
		this.objectFactory = new ObjectFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.IObjectWriter#saveObject(java.io.OutputStream, org.essembeh.rtfm.core.utils.version.ISaveable)
	 */
	@Override
	public void writeObject(OutputStream outputStream, Library element) throws WriterException {
		TLibraryV2 model = toModel(element);
		try {
			JaxbUtils.writeJaxbObject(outputStream, objectFactory.createLibrary(model), TLibraryV2.class);
		} catch (JAXBException e) {
			throw new WriterException(e);
		}
	}

	/**
	 * 
	 * @param musicFile
	 * @return
	 */
	private boolean isExportable(IXFile musicFile) {
		boolean isExportable = true;
		if (exportableAttribute != null) {
			isExportable = Boolean.TRUE.toString().equalsIgnoreCase(musicFile.getAttributes().getAttributeValue(exportableAttribute));
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
		for (IXFile musicFile : library.getAllFiles()) {
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
	protected TFile toModel(IXFile musicFile) {
		TFile model = objectFactory.createTFile();
		model.setType(musicFile.getType().toString());
		model.setVirtualpath(musicFile.getVirtualPath());
		Attributes attributeList = musicFile.getAttributes();
		for (String attributeName : attributeList.getAllNames()) {
			TAttribute modelAttribute = objectFactory.createTAttribute();
			modelAttribute.setName(attributeName);
			modelAttribute.setValue(attributeList.getAttributeValue(attributeName));
			model.getAttribute().add(modelAttribute);
		}
		return model;
	}

}
