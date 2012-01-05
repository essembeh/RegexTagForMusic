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
import org.essembeh.rtfm.model.library.version1.TFile;
import org.essembeh.rtfm.model.library.version1.TLibraryV1;

import com.google.inject.Inject;

public class LibraryLoaderV1 implements LibraryLoader {

	private static Logger logger = Logger.getLogger(LibraryLoaderV1.class);

	EventHandler eventHandler;

	TLibraryV1 model = null;

	@Inject
	public LibraryLoaderV1(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	@Override
	public boolean load(File source) {
		boolean rc = false;
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.library.version1");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
			JAXBElement<TLibraryV1> root = unmarshaller.unmarshal(new StreamSource(source), TLibraryV1.class);
			model = root.getValue();
			rc = (model != null);
		} catch (JAXBException e) {
			logger.info("Cannot load Library version 1 from file: " + source.getAbsolutePath());
		}
		return rc;
	}

	public File getRootFolder() throws LibraryException {
		if (model == null) {
			throw new LibraryException("LibraryLoader is not initialized");
		}
		File rootFolder = new File(model.getPath());
		return rootFolder;
	}

	public void update(IdList<MusicFile, Identifier<MusicFile>> listOfFiles) throws LibraryException {
		if (model == null) {
			throw new LibraryException("LibraryLoader is not initialized");
		}
		IdList<TFile, Identifier<TFile>> oldFiles = new IdList<TFile, Identifier<TFile>>(new Identifier<TFile>() {
			@Override
			public String getId(TFile o) {
				return o.getPath();
			}
		}, model.getFile());
		for (MusicFile musicFile : listOfFiles) {
			String key = musicFile.getVirtualPath();
			if (oldFiles.contains(key)) {
				updateMusicFile(musicFile, oldFiles.get(key));
			} else {
				eventHandler.loadLibraryNewFile(musicFile);
			}
		}
		for (TFile tFile : oldFiles) {
			eventHandler.loadLibraryOldFile(tFile.getType(), tFile.getPath());
		}
	}

	protected void updateMusicFile(MusicFile musicFile, TFile model) {
		Boolean isTagged = model.isTagged();
		if (isTagged != null) {
			logger.debug("Set file: " + model.getPath() + ", tagged: " + isTagged);
			Attribute attribute = new Attribute("tagged", isTagged.toString(), false);
			musicFile.getAttributeList().add(attribute);
		}
	}
}
