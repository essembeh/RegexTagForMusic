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

import org.essembeh.rtfm.core.conf.RTFMProperties;
import org.essembeh.rtfm.interfaces.IShellOutputWriter;

/**
 * 
 * @author seb
 * 
 */
public class ConsoleOutputWriter implements IShellOutputWriter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.shell.io.IShellOutputWriter#printMessage(java.lang.
	 * String)
	 */
	@Override
	public void printMessage(String message) {
		System.out.println(message);
		System.out.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.shell.io.IShellOutputWriter#printError(java.lang.String
	 * )
	 */
	@Override
	public void printError(String message) {
		System.err.println(message);
		System.err.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.shell.io.IShellOutputWriter#printException(java.lang
	 * .Exception)
	 */
	@Override
	public void printException(Exception e) {
		printError("ERROR: " + e.getMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.shell.io.IShellOutputWriter#printPrompt()
	 */
	@Override
	public void prompt() {
		String prompt = RTFMProperties.getProperty("shell.prompt");
		if (prompt == null) {
			prompt = "$ ";
		}
		System.out.print(prompt);
		System.out.flush();
	}
}
