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

package org.essembeh.rtfm.shell;

import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.ShellCommandInvalidArgument;
import org.essembeh.rtfm.core.exception.ShellQuit;
import org.essembeh.rtfm.core.services.Services;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.interfaces.IMusicFile;
import org.essembeh.rtfm.interfaces.IShellInputReader;
import org.essembeh.rtfm.interfaces.IShellOutputWriter;

/**
 * 
 * @author seb
 * 
 */
public class Shell {

	/**
	 * 
	 */
	private static Logger logger = Logger.getLogger(Shell.class);

	/**
	 * 
	 */
	protected MusicManager app;

	/**
	 * 
	 */
	protected IShellInputReader in;

	/**
	 * 
	 */
	protected IShellOutputWriter out;

	/**
	 * 
	 */
	protected boolean endOfLoop = false;

	/**
	 * 
	 * @param app
	 * @param in
	 * @param out
	 * @throws ConfigurationException
	 */
	public Shell(MusicManager app, IShellInputReader reader, IShellOutputWriter writer) throws ConfigurationException {
		this.app = app;
		this.out = writer;
		this.in = reader;
	}

	/**
	 * 
	 */
	public void loop() {
		logger.info("Begin shell loop");
		this.endOfLoop = false;
		while (!this.endOfLoop) {
			this.out.printMessage("");
			String command = this.in.readCommand(this.out);
			if (command == null) {
				logger.debug("Null command so quit loop");
				quit();
			} else {
				processCommand(StringUtils.stringToList(command, " "));
			}
		}
		logger.info("End shell loop");
	}

	/**
	 * 
	 * @param command
	 */
	protected void processCommand(List<String> args) {
		Shell.logger.debug("Process command: " + StringUtils.arrayToString(args.toArray(), " "));
		String commandName = args.get(0);
		ICommand command = Services.getShellCommandService().get(commandName);
		if (command != null) {
			try {
				int rc = command.execute(this.out, this.app, args);
				logger.debug("Command: " + command + " exited with rc=" + rc);
			} catch (ShellCommandInvalidArgument e) {
				this.out.printError("Invalid usage of command");
				this.out.printError(command.getHelp(commandName));
			} catch (ShellQuit e) {
				this.out.printMessage("Quit shell");
				quit();
			}
		} else {
			Shell.logger.warn("Cannot find command: " + commandName);
		}
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public static String fileToString(IMusicFile file) {
		StringBuilder out = new StringBuilder();
		if (file.isTaggable()) {
			if (file.isTagged()) {
				out.append("+ ");
			} else {
				out.append("- ");
			}
		} else {
			out.append("  ");
		}
		String id = "[" + file.getType() + "]";
		String formatedId = String.format("%-10s", id);
		out.append(formatedId).append(file.getVirtualPath());
		return out.toString();
	}

	/**
	 * 
	 */
	public void quit() {
		this.endOfLoop = true;
	}

}
