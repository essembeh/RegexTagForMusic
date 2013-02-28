package org.essembeh.rtfm.core.filehandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.configuration.CoreConfiguration;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.XFile;

import com.google.inject.Inject;

public class FileHandlerManager {

	private final static Logger LOGGER = Logger.getLogger(FileHandlerManager.class);
	private final CoreConfiguration configuration;

	/**
	 * 
	 * @param configuration
	 */
	@Inject
	public FileHandlerManager(CoreConfiguration configuration) {
		this.configuration = configuration;
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws DynamicAttributeException
	 */
	public IXFile createXFile(VirtualFile file) throws DynamicAttributeException {
		XFile out = null;
		for (FileHandler fileHandler : configuration.getFileHandlers()) {
			if (fileHandler.getConditions().isTrue(file)) {
				LOGGER.debug("Found filhandler: " + fileHandler + ", for file: " + file);
				out = new XFile(fileHandler.getType(), file);
				out.getAttributes().putAll(fileHandler.getAttributesForFile(out.getFile()));
				break;
			}
		}
		if (out == null) {
			LOGGER.info("Cannot find filehandler for file: " + file);
		}
		return out;
	}

	/**
	 * 
	 * @return
	 */
	public List<FileType> getDeclaredFileTypes() {
		List<FileType> out = new ArrayList<FileType>();
		for (FileHandler fileHandler : configuration.getFileHandlers()) {
			out.add(fileHandler.getType());
		}
		Collections.sort(out);
		return Collections.unmodifiableList(out);
	}
}
