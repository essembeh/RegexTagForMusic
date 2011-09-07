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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.interfaces.ICommand;

public class ShellCommandService {

	/**
	 * Class logger
	 */
	static final private Logger logger = Logger.getLogger(ShellCommandService.class);

	/**
	 * The data
	 */
	protected Map<String, ICommand> data = new HashMap<String, ICommand>();

	/**
	 * Registers a shell command
	 * 
	 * @param id
	 *            the identifier of the shell command
	 * @param object
	 *            the tag provider
	 * @param alternative
	 *            the alternative id
	 */
	public void add(String id, ICommand object, String alternative) {
		this.data.put(id, object);
		logger.debug("Adding new Command for id: " + id);
		if (alternative != null) {
			this.data.put(alternative, object);
			logger.debug("Adding new Command for alternative: " + alternative);
		}
	}

	/**
	 * Gets a specific shell command
	 * 
	 * @param id
	 * @return
	 */
	public ICommand get(String id) {
		ICommand object = this.data.get(id);
		logger.debug("Get the ICommand, id: " + id + ", Object: " + object);
		return object;
	}

	/**
	 * Returns all commands available.
	 * 
	 * @return the list
	 */
	public List<String> getListOfShellCommands() {
		List<String> list = new ArrayList<String>();
		list.addAll(this.data.keySet());
		Collections.sort(list);
		return list;
	}
}
