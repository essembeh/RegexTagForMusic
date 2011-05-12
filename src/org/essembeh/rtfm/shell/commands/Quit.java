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
import org.essembeh.rtfm.core.exception.ShellQuit;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.shell.io.IShellOutputWriter;

/**
 * 
 * @author seb
 * 
 */
public class Quit implements ICommand {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.interfaces.ICommand#execute(org.essembeh.rtfm.shell
	 * .io.IShellOutputWriter, org.essembeh.rtfm.core.MusicManager,
	 * java.util.List)
	 */
	@Override
	public int execute(IShellOutputWriter out, MusicManager app, List<String> args) throws ShellQuit {
		throw new ShellQuit();
	}

	@Override
	public String getHelp(String command) {
		return "Quit the shell";
	}
}
