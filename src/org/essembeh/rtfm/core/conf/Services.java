/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of rtfm.
 * 
 * rtfm is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * rtfm is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * rtfm. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core.conf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.FileHandler;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.interfaces.ITagProvider;
import org.essembeh.rtfm.interfaces.ITagWriter;

/**
 * @author seb
 * 
 */
public class Services {

	/**
	 * Class logger
	 */
	static protected Logger logger = Logger.getLogger(Services.class);

	/**
	 * The tag writers
	 */
	protected Map<String, ITagWriter> tagWriters;

	/**
	 * The tag providers
	 */
	protected Map<String, ITagProvider> tagProviders;

	/**
	 * All file handlers found in configuration file
	 */
	protected List<FileHandler> fileHandlers;

	/**
	 * All shell commands
	 */
	protected Map<String, ICommand> shellCommands;

	/**
	 * Private constructor for Singleton design pattern.
	 */
	protected Services() {
		this.tagProviders = new HashMap<String, ITagProvider>();
		this.tagWriters = new HashMap<String, ITagWriter>();
		this.fileHandlers = new ArrayList<FileHandler>();
		this.shellCommands = new HashMap<String, ICommand>();
	}

	/**
	 * Add the tag writer to the map of services
	 * 
	 * @param id
	 * @param tagProvider
	 */
	public void addTagWriter(String id, ITagWriter tagWriter) {
		this.tagWriters.put(id, tagWriter);
	}

	/**
	 * Get a specific tag writer
	 * 
	 * @param id
	 *            the key of the tag writer
	 * @return the tag writer
	 */
	public ITagWriter getTagWriter(String id) {
		ITagWriter tagWriter = this.tagWriters.get(id);
		return tagWriter;
	}

	/**
	 * Add the tag provider to the map of services
	 * 
	 * @param id
	 * @param tagProvider
	 */
	public void addTagProvider(String id, ITagProvider tagProvider) {
		this.tagProviders.put(id, tagProvider);
	}

	/**
	 * Get a specific tag provider
	 * 
	 * @param id
	 *            the key of the tag provider
	 * @return the tag provider
	 */
	public ITagProvider getTagProvider(String id) {
		ITagProvider tagProvider = this.tagProviders.get(id);
		return tagProvider;
	}

	/**
	 * Add file handler
	 * 
	 * @param fileHandler
	 */
	public void addFileHandler(FileHandler fileHandler) {
		this.fileHandlers.add(fileHandler);
	}

	/**
	 * Return the list of all file handlers
	 * 
	 * @return
	 */
	public List<FileHandler> getFileHandlers() {
		return this.fileHandlers;
	}

	/**
	 * 
	 * @param virtualPath
	 * @return
	 */
	public FileHandler getFileHandlerForFile(String virtualPath) {
		FileHandler fileHandler = null;
		for (FileHandler currentFileHandler : getFileHandlers()) {
			if (currentFileHandler.doesApplyForFile(virtualPath)) {
				fileHandler = currentFileHandler;
				break;
			}
		}
		logger.debug("For file: " + virtualPath + ", found handler: " + fileHandler);
		return fileHandler;
	}

	/**
	 * Add a shell command to the services
	 * 
	 * @param id
	 * @param command
	 */
	public void addShellCommand(String id, ICommand command, String alternative) {
		this.shellCommands.put(id, command);
		if (alternative != null && alternative.length() > 0) {
			this.shellCommands.put(alternative, command);
		}
	}

	/**
	 * @return the mapOfShellCommands
	 */
	public List<String> getListOfShellCommands() {
		List<String> list = new ArrayList<String>();
		list.addAll(this.shellCommands.keySet());
		Collections.sort(list);
		return list;
	}

	/**
	 * 
	 * @param command
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public ICommand getCommand(String command) {
		ICommand handler = this.shellCommands.get(command);
		return handler;
	}

	/**
	 * The singleton
	 */
	static protected Services instance = null;

	/**
	 * Get the Services instance
	 * 
	 * @return
	 */
	public static Services instance() {
		if (instance == null) {
			instance = new Services();
		}
		return instance;
	}

}
