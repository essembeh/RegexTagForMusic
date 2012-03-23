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
import org.essembeh.rtfm.model.library.version2.TAttribute;
import org.essembeh.rtfm.model.library.version2.TFile;
import org.essembeh.rtfm.model.library.version2.TLibraryV2;

import com.google.inject.Inject;

public class LibraryLoaderV2 implements ILibraryLoader {

	private static Logger logger = Logger.getLogger(LibraryLoaderV2.class);

	TLibraryV2 model = null;

	@Inject
	public LibraryLoaderV2() {
	}

	@Override
	public void loadLibrary(File source, LibraryLoaderCallback callback) throws LibraryException, IOException {
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.library.version2");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
			JAXBElement<TLibraryV2> root = unmarshaller.unmarshal(new StreamSource(source), TLibraryV2.class);
			model = root.getValue();
		} catch (JAXBException e) {
			logger.info("Cannot load Library version 2 from file: " + source.getAbsolutePath());
			throw new LibraryException(e);
		}

		IdList<TFile, Identifier<TFile>> modelFiles = new IdList<TFile, Identifier<TFile>>(new Identifier<TFile>() {
			@Override
			public String getId(TFile o) {
				return o.getVirtualpath();
			}
		}, model.getFile());

		callback.setRootFolder(new File(model.getRootFolder().getPath()));

		for (TFile tFile : modelFiles) {
			IMusicFile musicFile = callback.getExistingMusicFile(tFile.getVirtualpath(),
					FileType.getFiletype(tFile.getType()));
			if (musicFile != null) {
				updateMusicFile(musicFile, tFile);
			}
		}
	}

	protected void updateMusicFile(IMusicFile musicFile, TFile model) {
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
