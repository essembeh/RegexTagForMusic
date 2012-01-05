package org.essembeh.rtfm.core.library.io;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.model.library.version2.ObjectFactory;
import org.essembeh.rtfm.model.library.version2.TAttribute;
import org.essembeh.rtfm.model.library.version2.TFile;
import org.essembeh.rtfm.model.library.version2.TLibraryV2;
import org.essembeh.rtfm.model.library.version2.TRootFolder;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class LibraryWriterV2 implements LibraryWriter {

	ObjectFactory objectFactory;

	String exportableAttribute = null;

	@Inject
	public LibraryWriterV2(ObjectFactory objectFactory, @Named("rtfm.properties") RTFMProperties properties) {
		this.objectFactory = objectFactory;
		exportableAttribute = properties.getProperty("library.musicfile.attribute.export");
	}

	@Override
	public void write(File destination, IdList<MusicFile, Identifier<MusicFile>> musicFiles, File rootFolder)
			throws LibraryException {
		TLibraryV2 library = toModel(musicFiles, rootFolder);

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

	protected boolean isExportable(MusicFile musicFile) {
		boolean isExportable = true;
		if (exportableAttribute != null) {
			Attribute exportAttribute = musicFile.getAttributeList().get(exportableAttribute);
			if (Boolean.FALSE.toString().equalsIgnoreCase(exportAttribute.getValue())) {
				isExportable = false;
			}
		}
		return isExportable;
	}

	protected TLibraryV2 toModel(IdList<MusicFile, Identifier<MusicFile>> musicFiles, File rootFolder) {
		TLibraryV2 model = objectFactory.createTLibraryV2();
		model.setRootFolder(toModel(rootFolder));
		for (MusicFile musicFile : musicFiles) {
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

	protected TFile toModel(MusicFile musicFile) {
		TFile model = objectFactory.createTFile();
		model.setType(musicFile.getType());
		model.setVirtualpath(musicFile.getVirtualPath());
		for (Attribute a : musicFile.getAttributeList()) {
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
