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

package org.essembeh.rtfm.shell.commands;

import java.util.List;

import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.conf.Services;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.interfaces.IShellOutputWriter;

/**
 * 
 * @author seb
 * 
 */
public class Help implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.interfaces.ICommand#execute(org.essembeh.rtfm.shell
	 * .io.IShellOutputWriter, org.essembeh.rtfm.core.MusicManager,
	 * java.util.List)
	 */
	@Override
	public int execute(IShellOutputWriter out, MusicManager app, List<String> args) {
		if (args.size() == 2) {
			String command = args.get(1);
			ICommand commandHandler = Services.instance().getCommand(command);
			if (commandHandler == null) {
				out.printMessage("Command not found: " + command);
			} else {
				out.printMessage(commandHandler.getHelp(command));
			}
		} else {
			out.printMessage(getHelp(args.get(0)));
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ICommand#getHelp(java.lang.String)
	 */
	@Override
	public String getHelp(String command) {
		StringBuilder out = new StringBuilder();
		out.append("Usage: ").append(command).append(" <command>").append("\n");
		out.append("Available commands are: ");
		for (String thecommand : Services.instance().getListOfShellCommands()) {
			out.append(thecommand).append(" ");
		}
		return out.toString();
	}
}
