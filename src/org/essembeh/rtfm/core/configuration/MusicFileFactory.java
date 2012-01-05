package org.essembeh.rtfm.core.configuration;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.configuration.io.CoreConfigurationLoader;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.filehandler.FileHandlerImpl;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.FileUtils;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class MusicFileFactory {

	private static Logger logger = Logger.getLogger(MusicFileFactory.class);

	List<FileHandlerImpl> fileHandlers;

	@Inject
	public MusicFileFactory(@Named("rtfm.properties") RTFMProperties properties,
							CoreConfigurationLoader coreConfigurationLoader) throws ConfigurationException,
			FileNotFoundException {
		String configurationFilename = properties.getMandatoryProperty("configuration.core");
		InputStream resource = FileUtils.getResourceAsStream(configurationFilename);
		coreConfigurationLoader.load(resource);
		fileHandlers = coreConfigurationLoader.getFileHandlers();
	}

	public MusicFile createMusicFile(VirtualFile file) throws InstantiationException, DynamicAttributeException {
		MusicFile musicFile = null;
		for (FileHandlerImpl fileHandler : fileHandlers) {
			if (fileHandler.canHandle(file)) {
				musicFile = fileHandler.create(file);
				break;
			}
		}
		if (musicFile == null) {
			logger.info("Cannot find filehandler for file: " + file);
		}
		return musicFile;
	}

	public List<String> getAllTypes() {
		List<String> list = new ArrayList<String>();
		for (FileHandler fileHandler : fileHandlers) {
			list.add(fileHandler.getId());
		}
		return list;
	}

	@Override
	public String toString() {
		return "CoreConfiguration [filehandlers: {" + StringUtils.join(fileHandlers, ", ") + "}]";
	}

}
