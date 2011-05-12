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
package org.essembeh.rtfm.shell.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

/**
 * 
 * @author seb
 * 
 */
public class ConsoleInputReader implements IShellInputReader {

	/**
	 * 
	 */
	static private Logger logger = Logger.getLogger(ConsoleInputReader.class);

	/**
	 * 
	 */
	protected BufferedReader br = null;

	/**
	 * Constructor
	 */
	public ConsoleInputReader() {
		this.br = new BufferedReader(new InputStreamReader(System.in));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.shell.io.IShellInputReader#readCommand(org.essembeh
	 * .rtfm.shell.io.IShellOutputWriter)
	 */
	@Override
	public String readCommand(IShellOutputWriter out) {
		String command = null;
		try {
			do {
				out.prompt();
				command = this.br.readLine();
				if (command != null) {
					command = command.trim();
				}
			} while (!isValidCommand(command));
		} catch (IOException e) {
			logger.error("Error while reading the next command: " + e.getMessage());
			command = null;
		}
		return command;
	}

	/**
	 * 
	 * @param command
	 * @return
	 */
	protected boolean isValidCommand(String command) {
		boolean rc = command == null || (command.length() > 0 && !command.startsWith(COMMENT_START));
		logger.debug("isValidCommand: " + command + " => " + rc);
		return rc;
	}
}
