package org.essembeh.rtfm.core.configuration.io;

import java.util.List;

import org.essembeh.rtfm.core.actions.Action;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public interface ICoreConfigurationLoader {

	List<FileHandler> getFileHandlers() throws ConfigurationException;

	IdList<Action, Identifier<Action>> getActions() throws ConfigurationException;
}