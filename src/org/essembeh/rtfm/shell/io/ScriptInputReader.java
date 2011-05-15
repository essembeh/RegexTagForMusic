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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.interfaces.IShellOutputWriter;

/**
 * 
 * @author seb
 * 
 */
public class ScriptInputReader extends ConsoleInputReader {

	/**
	 * Logger.
	 */
	private static Logger logger = Logger.getLogger(ScriptInputReader.class);

	/**
	 * Constructor
	 * 
	 * @throws FileNotFoundException
	 */
	public ScriptInputReader(File script) throws FileNotFoundException {
		this.br = new BufferedReader(new InputStreamReader(new FileInputStream(script)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.shell.io.ConsoleInputReader#readCommand(org.essembeh
	 * .rtfm.interfaces.IShellOutputWriter)
	 */
	@Override
	public String readCommand(IShellOutputWriter out) {
		String command = null;
		try {
			do {
				command = this.br.readLine();
				if (command != null) {
					command = command.trim();
				}
			} while (!isValidCommand(command));
		} catch (IOException e) {
			logger.error("Error while reading the next command: " + e.getMessage());
			command = null;
		}
		if (command != null) {
			out.prompt();
			out.printMessage(command);
		}
		return command;
	}
}
