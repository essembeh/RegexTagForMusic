package org.essembeh.rtfm.core.configuration.io;

import java.util.List;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public interface ICoreConfigurationLoader {

	List<FileHandler> getFileHandlers() throws ConfigurationException;

	IdList<Workflow, Identifier<Workflow>> getWorkflows() throws ConfigurationException;
}
