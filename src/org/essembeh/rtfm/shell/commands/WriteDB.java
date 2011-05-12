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

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.exception.ShellCommandInvalidArgument;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.shell.io.IShellOutputWriter;

public class WriteDB implements ICommand {

	Logger logger = Logger.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.interfaces.ICommand#execute(org.essembeh.rtfm.shell
	 * .io.IShellOutputWriter, org.essembeh.rtfm.core.MusicManager,
	 * java.util.List)
	 */
	@Override
	public int execute(IShellOutputWriter out, MusicManager app, List<String> args) throws ShellCommandInvalidArgument {
		int rc = 0;
		if (args.size() == 2) {
			try {
				File database = new File(args.get(1));
				app.writeDatabase(database);
				out.printMessage("Database successfully written: " + database.getAbsolutePath());
			} catch (Exception e) {
				out.printException(e);
				rc = 1;
			}
		} else {
			throw new ShellCommandInvalidArgument();
		}
		return rc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ICommand#getHelp(java.lang.String)
	 */
	@Override
	public String getHelp(String command) {
		return "Write database to file: " + command + "/path/to/file.xml";
	}
}
