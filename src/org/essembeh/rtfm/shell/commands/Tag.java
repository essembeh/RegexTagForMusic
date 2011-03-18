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

import org.essembeh.rtfm.core.MusicFile;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.shell.Shell;

public class Tag extends Show {

	/**
	 * 
	 * @param shell
	 * @param app
	 * @param list
	 */
	protected void executeOnList(Shell shell, MusicManager app, List<MusicFile> list) {
		int totalCount = list.size();
		int errorCount = app.tagList(list, false);
		shell.sysout("Tagged " + totalCount + StringUtils.plural(" file", totalCount) + " with " + errorCount
				+ StringUtils.plural(" error", errorCount));
		if (errorCount > 0) {
			shell.sysout("List of non tagged files: ");
			for (MusicFile musicFile : list) {
				shell.sysout(Shell.fileToString(musicFile));
			}
		}
	}
}
