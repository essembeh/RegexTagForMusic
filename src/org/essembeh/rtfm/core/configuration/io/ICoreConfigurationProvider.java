package org.essembeh.rtfm.core.configuration.io;

import java.util.List;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.version.IReader;

public interface ICoreConfigurationProvider extends IReader {

	/**
	 * 
	 * @return
	 */
	List<FileHandler> getFileHandlers();

	/**
	 * 
	 * @return
	 */
	List<Workflow> getWorkflows();
}
