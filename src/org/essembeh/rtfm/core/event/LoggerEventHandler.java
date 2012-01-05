package org.essembeh.rtfm.core.event;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public class LoggerEventHandler implements EventHandler {

	private static Logger logger = Logger.getLogger(LoggerEventHandler.class);

	@Override
	public void errorInstantiateFile(VirtualFile file, String comment) {
		logger.info("Error file instantiation, file:" + file + ", message:" + comment);
	}

	@Override
	public void errorMatchingDynamicAttribute(VirtualFile file, String comment) {
		logger.info("Error with dynamic attribute, file:" + file + ", message:" + comment);

	}

	@Override
	public void noFileHandlerForFile(VirtualFile file) {
		logger.info("No file handler found for file:" + file);
	}

	@Override
	public void loadLibraryNewFile(MusicFile musicFile) {
		logger.info("New file in Library: " + musicFile);
	}

	@Override
	public void loadLibraryOldFile(String type, String virtualpath) {
		logger.info("Old file no more present in Library, type:" + type + ", path:" + virtualpath);
	}

}
