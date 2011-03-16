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

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.MusicFile;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.exception.ShellCommandInvalidArgument;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.shell.Shell;

public class Data implements ICommand {

	Logger logger = Logger.getLogger(getClass());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.interfaces.ICommand#execute(org.essembeh.rtfm.shell
	 * .Shell, org.essembeh.rtfm.core.MusicManager, java.util.List)
	 */
	@Override
	public int execute(Shell shell, MusicManager app, List<String> args) throws ShellCommandInvalidArgument {
		int rc = 0;
		if (args.size() == 2) {
			try {
				int filenumber = Integer.parseInt(args.get(1));
				MusicFile file = app.getAllFiles().get(filenumber);
				if (file.isTaggable()) {
					TagData data = file.getTagData();
					shell.sysout("File: " + file);
					shell.sysout("Tag data: " + data);
				} else {
					shell.syserr("The file is not taggable: " + file);
				}
			} catch (Exception e) {
				this.logger.error(e.getMessage());
				rc = -1;
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
		return "Usage: " + command + " FILENUMBER";
	}

}
