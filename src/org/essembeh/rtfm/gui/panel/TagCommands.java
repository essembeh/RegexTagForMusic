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

import org.essembeh.rtfm.gui.action.InspectAction;
import org.essembeh.rtfm.gui.action.TagAllAction;
import org.essembeh.rtfm.gui.action.TagSelectionAction;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.jdesktop.swingx.JXTaskPane;

public class TagCommands extends JXTaskPane {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5429472164954626078L;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public TagCommands(RTFMController controller) {
		super("Tag operations");
		add(new InspectAction(controller));
		add(new TagSelectionAction(controller));
		add(new TagAllAction(controller));
	}
}
