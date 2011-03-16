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
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.Filter.Status;
import org.essembeh.rtfm.core.MusicFile;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.exception.ShellCommandInvalidArgument;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.interfaces.ICommand;
import org.essembeh.rtfm.shell.Shell;

public class Show implements ICommand {

	enum ShowWhat {
		ALL, NEW, TAGGED, TAGGABLE, NONTAGGABLE, TYPE
	}

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

		ShowWhat showWhat = null;
		String type = null;

		if (args.size() > 3) {
			throw new ShellCommandInvalidArgument();
		}
		if (args.size() == 1) {
			showWhat = ShowWhat.ALL;
		} else if (args.size() > 1) {
			try {
				showWhat = ShowWhat.valueOf(args.get(1).toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new ShellCommandInvalidArgument(1);
			}
			if (showWhat == ShowWhat.TYPE) {
				if (args.size() == 3) {
					type = args.get(2);
				} else {
					throw new ShellCommandInvalidArgument(2);
				}
			} else {
				if (args.size() == 3) {
					throw new ShellCommandInvalidArgument(2);
				}
			}
		}

		show(shell, app, showWhat, type);

		return rc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.interfaces.ICommand#getHelp(java.lang.String)
	 */
	@Override
	public String getHelp(String command) {
		StringBuilder out = new StringBuilder();
		out.append("Display files in MusicManager.\n");
		ShowWhat[] allArg1 = ShowWhat.values();
		String[] array = new String[allArg1.length];
		for (int i = 0; i < ShowWhat.values().length; i++) {
			array[i] = allArg1[i].name().toLowerCase();
		}
		out.append("Usage: " + command + " [" + StringUtils.arrayToString(array, "|") + "]\n");
		out.append("Usage: " + command + " type <ANY_TYPE>");
		return out.toString();
	}

	/**
	 * 
	 * @param shell
	 * @param app
	 * @param showWhat2
	 * @param type
	 */
	private void show(Shell shell, MusicManager app, ShowWhat showWhat, String type) {
		List<MusicFile> list = new ArrayList<MusicFile>();
		switch (showWhat) {
		case ALL:
			this.logger.debug("Show all");
			list.addAll(app.getAllFiles());
			break;
		case NEW:
			this.logger.debug("Show taggable non tagged");
			list.addAll(app.getFilteredFiles(Filter.NON_TAGGED));
			break;
		case TAGGED:
			this.logger.debug("Show taggable and tagged");
			list.addAll(app.getFilteredFiles(new Filter(Status.ENABLE, Status.ENABLE, null)));
			break;
		case TAGGABLE:
			this.logger.debug("Show taggable");
			list.addAll(app.getFilteredFiles(Filter.TAGGABLE));
			break;
		case NONTAGGABLE:
			this.logger.debug("Show nontaggable");
			list.addAll(app.getFilteredFiles(new Filter(Status.INVERSE, Status.NO_FILTER, null)));
			break;
		case TYPE:
			list.addAll(app.getFilteredFiles(new Filter(Status.NO_FILTER, Status.NO_FILTER, Pattern.compile(type))));
			break;
		}
		for (int i = 0; i < list.size(); i++) {
			shell.sysout(i + ": " + Shell.fileToString(list.get(i)));
		}
		shell.sysout(list.size() + StringUtils.plural(" file", list.size()));
	}
}
