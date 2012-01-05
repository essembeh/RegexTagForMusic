package org.essembeh.rtfm.core.configuration.io;

import java.io.InputStream;
import java.util.List;

import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.filehandler.FileHandlerImpl;

public interface CoreConfigurationLoader {

	boolean load(InputStream source);

	List<FileHandlerImpl> getFileHandlers() throws ConfigurationException;

}
