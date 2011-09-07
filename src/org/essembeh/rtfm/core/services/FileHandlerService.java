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
package org.essembeh.rtfm.core.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.FileHandler;

public class FileHandlerService {

	/**
	 * Class logger
	 */
	static final private Logger logger = Logger.getLogger(FileHandlerService.class);

	/**
	 * The data
	 */
	protected static List<FileHandler> data = new ArrayList<FileHandler>();

	/**
	 * Finds a file handler that applies for the given virtual path.
	 * 
	 * @param virtualPath
	 * @return
	 */
	public FileHandler getFileHandlerForFile(String virtualPath) {
		FileHandler fileHandler = null;
		for (FileHandler currentFileHandler : data) {
			if (currentFileHandler.doesApplyForFile(virtualPath)) {
				fileHandler = currentFileHandler;
				break;
			}
		}
		logger.debug("For file: " + virtualPath + ", found handler: " + fileHandler);
		return fileHandler;
	}

	/**
	 * Registers a file handler
	 * 
	 * @param object
	 *            the tag provider
	 */
	public static void add(FileHandler object) {
		data.add(object);
		logger.debug("Adding new FileHandler: " + object);
	}
}
