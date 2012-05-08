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
package org.essembeh.rtfm.core.configuration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.configuration.io.ICoreConfigurationLoader;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

import com.google.inject.Inject;

public class MusicFileService {

	private static Logger logger = Logger.getLogger(MusicFileService.class);

	private final List<FileHandler> fileHandlers;

	@Inject
	public MusicFileService(ICoreConfigurationLoader coreConfigurationLoader) throws ConfigurationException {
		logger.debug("Init MusicFileService");
		fileHandlers = coreConfigurationLoader.getFileHandlers();
	}

	public IMusicFile createMusicFile(VirtualFile file) throws DynamicAttributeException {
		MusicFile musicFile = null;
		for (FileHandler fileHandler : fileHandlers) {
			if (fileHandler.canHandle(file)) {
				musicFile = new MusicFile(fileHandler.getType(), file);
				musicFile.getAttributeList().addAll(fileHandler.getAttributesForFile(musicFile.getFile()));
				break;
			}
		}
		if (musicFile == null) {
			logger.info("Cannot find filehandler for file: " + file);
		}
		return musicFile;
	}

	@Override
	public String toString() {
		return "CoreConfiguration [filehandlers: {" + StringUtils.join(fileHandlers, ", ") + "}]";
	}

}
