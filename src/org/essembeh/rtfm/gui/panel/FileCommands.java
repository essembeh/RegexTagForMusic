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
package org.essembeh.rtfm.gui.panel;

import org.essembeh.rtfm.gui.action.ReadDBAction;
import org.essembeh.rtfm.gui.action.ScanFolderAction;
import org.essembeh.rtfm.gui.action.WriteDBAction;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.jdesktop.swingx.JXTaskPane;

public class FileCommands extends JXTaskPane {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -1165362619575471349L;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public FileCommands(RTFMController controller) {
		super("Music management");
		add(new ScanFolderAction(controller));
		add(new ReadDBAction(controller));
		add(new WriteDBAction(controller));
	}
}
