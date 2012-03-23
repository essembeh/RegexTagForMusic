package org.essembeh.rtfm.core.library.io;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.model.library.version1.TFile;
import org.essembeh.rtfm.model.library.version1.TLibraryV1;

import com.google.inject.Inject;

public class LibraryLoaderV1 implements ILibraryLoader {

	private static Logger logger = Logger.getLogger(LibraryLoaderV1.class);

	TLibraryV1 model = null;

	@Inject
	public LibraryLoaderV1() {
	}

	@Override
	public void loadLibrary(File source, LibraryLoaderCallback callback) throws LibraryException, IOException {
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.library.version1");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
			JAXBElement<TLibraryV1> root = unmarshaller.unmarshal(new StreamSource(source), TLibraryV1.class);
			model = root.getValue();
		} catch (JAXBException e) {
			logger.info("Cannot load Library version 1 from file: " + source.getAbsolutePath());
			throw new LibraryException(e);
		}

		IdList<TFile, Identifier<TFile>> modelFiles = new IdList<TFile, Identifier<TFile>>(new Identifier<TFile>() {
			@Override
			public String getId(TFile o) {
				return o.getPath();
			}
		}, model.getFile());

		callback.setRootFolder(new File(model.getPath()));

		for (TFile tFile : modelFiles) {
			IMusicFile musicFile = callback
					.getExistingMusicFile(tFile.getPath(), FileType.getFiletype(tFile.getType()));
			if (musicFile != null) {
				updateMusicFile(musicFile, tFile);
			}
		}
	}

	protected void updateMusicFile(IMusicFile musicFile, TFile model) {
		Boolean isTagged = model.isTagged();
		if (isTagged != null) {
			logger.debug("Set file: " + model.getPath() + ", tagged: " + isTagged);
			Attribute attribute = new Attribute("tagged", isTagged.toString(), false);
			musicFile.getAttributeList().add(attribute);
		}
	}
}