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
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.interfaces.IMusicFile;
import org.essembeh.rtfm.interfaces.IShellOutputWriter;

public class Tag extends Show {

	/**
	 * Default constructor to set default argument.
	 */
	public Tag() {
		this.defaultArg = ShowWhat.NEW;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.shell.commands.Show#executeOnList(org.essembeh.rtfm
	 * .shell.io.IShellOutputWriter, org.essembeh.rtfm.core.MusicManager,
	 * java.util.List)
	 */
	@Override
	protected void executeOnList(IShellOutputWriter out, MusicManager app, List<IMusicFile> list) {
		int totalCount = list.size();
		int errorCount = 0;
		for (int i = 0; i < list.size(); i++) {
			String message = "[" + (i + 1) + "/" + (totalCount) + "] Tagging file " + list.get(i).getVirtualPath();
			try {
				list.get(i).tag(false);
				out.printMessage(message + ": OK");
			} catch (Exception e) {
				errorCount++;
				logger.error("Error while tagging file: " + list.get(i) + ", Error: " + e.getMessage());
				out.printError(message + ": FAIL");
				out.printException(e);
			}
		}
		out.printMessage(totalCount + StringUtils.plural(" file", totalCount) + " tagged with " + errorCount
				+ StringUtils.plural(" error", errorCount));
	}
}
