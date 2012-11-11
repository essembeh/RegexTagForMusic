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
package org.essembeh.rtfm.core.configuration.io;

import java.io.InputStream;
import java.util.List;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public interface ICoreConfigurationLoader {

	/**
	 * 
	 * @param input
	 * @throws ConfigurationException
	 */
	void loadConfiguration(InputStream input) throws ConfigurationException;

	/**
	 * 
	 * @return
	 * @throws ConfigurationException
	 */
	List<FileHandler> getFileHandlers() throws ConfigurationException;

	/**
	 * 
	 * @return
	 * @throws ConfigurationException
	 */
	IdList<Workflow, Identifier<Workflow>> getWorkflows() throws ConfigurationException;
}
