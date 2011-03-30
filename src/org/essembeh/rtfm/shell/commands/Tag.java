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
import org.essembeh.rtfm.interfaces.IMusicFile;
import org.essembeh.rtfm.shell.Shell;

public class Tag extends Show {

	/**
	 * 
	 * @param shell
	 * @param app
	 * @param list
	 */
	protected void executeOnList(Shell shell, MusicManager app, List<IMusicFile> list) {
		int totalCount = list.size();
		for (int i = 0; i < list.size(); i++) {
			shell.print("[" + (i+1) + "/" + (totalCount+1) + "] Tagging file " + list.get(i).getVirtualPath());
			try {
				app.tagFile(list.get(i), false);
				shell.println(": OK");
			} catch (Exception e) {
				shell.println(": ERROR " + e.getMessage());
			}
		}
	}
}
