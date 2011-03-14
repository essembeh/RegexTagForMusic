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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.MusicFile;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.exception.ShellCommandInvalidArgument;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.shell.Shell;

public class Tag implements ICommand {

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

		boolean tagAll = false;
		boolean dryrun = false;

		if ((args.size() == 2) || (args.size() == 3)) {
			String arg1 = args.get(1);
			if (arg1.equals("all")) {
				tagAll = true;
			} else if (arg1.equals("new")) {
				tagAll = false;
			} else {
				throw new ShellCommandInvalidArgument(1);
			}
			if (args.size() == 3) {
				if (args.get(2).equals("dryrun")) {
					dryrun = true;
				} else {
					throw new ShellCommandInvalidArgument(2);
				}
			}
		} else {
			throw new ShellCommandInvalidArgument();
		}

		List<MusicFile> listOfFilesToTag = new ArrayList<MusicFile>();
		if (tagAll) {
			listOfFilesToTag.addAll(app.getFilteredFiles(Filter.TAGGABLE));
		} else {
			listOfFilesToTag.addAll(app.getFilteredFiles(Filter.NON_TAGGED));
		}
		int totalCount = listOfFilesToTag.size();
		int errorCount = app.tagList(listOfFilesToTag, dryrun);
		shell.sysout("Tagged " + totalCount + StringUtils.plural(" file", totalCount) + " with " + errorCount
				+ StringUtils.plural(" error", errorCount));
		if (errorCount > 0) {
			shell.sysout("List of non tagged files: ");
			for (MusicFile musicFile : listOfFilesToTag) {
				shell.sysout(Shell.fileToString(musicFile));
			}
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
		return "Usage: " + command + " <all|new> [dryrun]";
	}

}
