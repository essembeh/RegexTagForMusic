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
package org.essembeh.rtfm.gui.tables;

import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.listener.Inspect;
import org.essembeh.rtfm.gui.renderers.MusicFileRenderer;
import org.essembeh.rtfm.gui.renderers.ThreeStatesBooleanRenderer;
import org.essembeh.rtfm.interfaces.IMusicFile;
import org.jdesktop.swingx.JXTable;

public class MusicTable extends JXTable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 2997618588768128182L;

	/**
	 * Attributes
	 */
	protected RTFMController controller;

	/**
	 * Constructor
	 * 
	 * @param model
	 */
	public MusicTable(RTFMController controller) {
		super(controller.getModel());

		// Set the controller
		this.controller = controller;

		// Double click to inspect
		addMouseListener(new Inspect(controller));

		// Column 3 special 3 states renderer
		setDefaultRenderer(Boolean.class, new ThreeStatesBooleanRenderer());
		setDefaultRenderer(IMusicFile.class, new MusicFileRenderer());

		// Column size
		getColumn(0).setMaxWidth(200);
		getColumn(2).setMaxWidth(70);
	}
}
