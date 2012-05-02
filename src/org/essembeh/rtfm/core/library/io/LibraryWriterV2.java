package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.essembeh.rtfm.core.exception.LibraryException;
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

	ObjectFactory objectFactory;

	String exportableAttribute = null;

	@Inject
	public LibraryWriterV2(RTFMProperties properties) {
		exportableAttribute = properties.getProperty("library.musicfile.attribute.export");
		this.objectFactory = new ObjectFactory();
	}

	@Override
	public void writeLibrary(File destination, LibraryWriterCallback callback) throws LibraryException {
		TLibraryV2 library = toModel(callback.getMusicFiles(), callback.getRootFolder());
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.library.version2");
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			JAXBElement<TLibraryV2> root = objectFactory.createLibrary(library);
			marshaller.marshal(root, destination);
		} catch (JAXBException e) {
			throw new LibraryException(e.getMessage());
		}
	}

	protected boolean isExportable(IMusicFile musicFile) {
		boolean isExportable = true;
		if (exportableAttribute != null) {
			Attribute exportAttribute = musicFile.getAttributeList().get(exportableAttribute);
			if (Boolean.FALSE.toString().equalsIgnoreCase(exportAttribute.getValue())) {
				isExportable = false;
			}
		}
		return isExportable;
	}

	protected TLibraryV2 toModel(List<IMusicFile> musicFiles, File rootFolder) {
		TLibraryV2 model = objectFactory.createTLibraryV2();
		model.setRootFolder(toModel(rootFolder));
		for (IMusicFile musicFile : musicFiles) {
			if (isExportable(musicFile)) {
				model.getFile().add(toModel(musicFile));
			}
		}
		return model;
	}

	protected TRootFolder toModel(File rootFolder) {
		TRootFolder model = objectFactory.createTRootFolder();
		model.setPath(rootFolder.getAbsolutePath());
		return model;
	}

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

	protected TAttribute toModel(Attribute attribute) {
		TAttribute model = objectFactory.createTAttribute();
		model.setName(attribute.getName());
		model.setValue(attribute.getValue());
		return model;
	}
}
