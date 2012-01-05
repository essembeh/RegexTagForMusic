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
package org.essembeh.rtfm.gui.panel.side.commands;

import java.util.Map;

import org.essembeh.rtfm.gui.action.DynamicAction;
import org.essembeh.rtfm.gui.controller.GuiController;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;
import org.jdesktop.swingx.JXTaskPane;

public class ActionCommands extends JXTaskPane {

	private static final long serialVersionUID = -5429472164954626078L;

	GuiController controller;

	public ActionCommands(GuiController controller) {
		super(Translator.get(StringId.titleActionsOnAll));
		this.controller = controller;
	}

	public void updateDynamicActions(Map<String, Integer> actions) {
		getContentPane().removeAll();
		for (String action : actions.keySet()) {
			int count = actions.get(action);
			add(new DynamicAction(controller, action, action + " (" + count + ")"));
		}
		this.updateUI();
	}
}
