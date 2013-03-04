package org.essembeh.rtfm.core.filehandler;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.configuration.CoreConfiguration;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.library.file.IVirtualFile;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.XFile;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.properties.SpecialAttribute;

import com.google.inject.Inject;

public class FileHandlerManager {

	private final static Logger LOGGER = Logger.getLogger(FileHandlerManager.class);
	private final CoreConfiguration configuration;
	private final RTFMProperties properties;

	/**
	 * 
	 * @param configuration
	 * @param properties
	 */
	@Inject
	public FileHandlerManager(CoreConfiguration configuration, RTFMProperties properties) {
		this.configuration = configuration;
		this.properties = properties;
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public IXFile createXFile(VirtualFile file) {
		XFile out = null;
		FileHandler fh = getFileHandlerForFile(file);
		if (fh != null) {
			LOGGER.debug("Found filhandler: " + fh + ", for file: " + file);
			out = new XFile(fh.getFileType(), file);
			// Attributes
			for (IDynamicAttribute attribute : fh.getAttributes()) {
				try {
					String value = attribute.getValue(file);
					if (value != null) {
						out.getAttributes().updateOrCreate(attribute.getName(), value);
					} else {
						LOGGER.debug("Null valud for attribute: " + attribute);
					}
				} catch (DynamicAttributeException e) {
					LOGGER.error("Error with attribute: " + attribute, e);
					out.getAttributes().appendAttributeValue(properties.getSpecialAttribute(SpecialAttribute.ERROR), e.getMessage());
				}
			}
		} else {
			LOGGER.info("Cannot find filehandler for file: " + file);
		}
		return out;
	}

	/**
	 * Searches for a valid filehandler
	 * 
	 * @param virtualFile
	 * @return
	 */
	private FileHandler getFileHandlerForFile(IVirtualFile virtualFile) {
		for (FileHandler fh : configuration.getFileHandlers()) {
			if (fh.getConditions().isTrue(virtualFile)) {
				return fh;
			}
		}
		return null;
	}
}
