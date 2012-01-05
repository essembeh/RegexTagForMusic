package org.essembeh.rtfm.core.library.io;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.event.EventHandler;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.model.library.version2.TAttribute;
import org.essembeh.rtfm.model.library.version2.TFile;
import org.essembeh.rtfm.model.library.version2.TLibraryV2;

import com.google.inject.Inject;

public class LibraryLoaderV2 implements LibraryLoader {

	private static Logger logger = Logger.getLogger(LibraryLoaderV2.class);

	TLibraryV2 model = null;
	EventHandler eventHandler;

	@Inject
	public LibraryLoaderV2(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	@Override
	public boolean load(File source) {
		boolean rc = false;
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.library.version2");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
			JAXBElement<TLibraryV2> root = unmarshaller.unmarshal(new StreamSource(source), TLibraryV2.class);
			model = root.getValue();
			rc = (model != null);
		} catch (JAXBException e) {
			logger.info("Cannot load Library version 2 from file: " + source.getAbsolutePath());
		}
		return rc;
	}

	public File getRootFolder() throws LibraryException {
		if (model == null) {
			throw new LibraryException("LibraryLoader is not initialized");
		}
		File rootFolder = new File(model.getRootFolder().getPath());
		return rootFolder;
	}

	public void update(IdList<MusicFile, Identifier<MusicFile>> listOfFiles) throws LibraryException {
		if (model == null) {
			throw new LibraryException("LibraryLoader is not initialized");
		}
		IdList<TFile, Identifier<TFile>> oldFiles = new IdList<TFile, Identifier<TFile>>(new Identifier<TFile>() {
			@Override
			public String getId(TFile o) {
				return o.getVirtualpath();
			}
		}, model.getFile());
		for (MusicFile musicFile : listOfFiles) {
			String key = musicFile.getVirtualPath();
			if (oldFiles.contains(key)) {
				updateMusicFile(musicFile, oldFiles.get(key));
				oldFiles.remove(key);
			} else {
				eventHandler.loadLibraryNewFile(musicFile);
			}
		}
		for (TFile tFile : oldFiles) {
			eventHandler.loadLibraryOldFile(tFile.getType(), tFile.getVirtualpath());
		}
	}

	protected void updateMusicFile(MusicFile musicFile, TFile model) {
		for (TAttribute attribute : model.getAttribute()) {
			String name = attribute.getName();
			String value = attribute.getValue();
			if (musicFile.getAttributeList().contains(name)) {
				// Update
				musicFile.getAttributeList().get(name).setValue(value);
			} else {
				// Create
				musicFile.getAttributeList().add(new Attribute(name, value, false));
			}
		}
	}
}
