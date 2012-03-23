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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.gui.controller.GuiController;
import org.essembeh.rtfm.gui.menu.ContextualMenu;
import org.essembeh.rtfm.gui.renderers.MusicFileRenderer;
import org.essembeh.rtfm.gui.renderers.ThreeStatesBooleanRenderer;
import org.jdesktop.swingx.JXTable;

public class MusicTable extends JXTable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 2997618588768128182L;

	/**
	 * Constructor
	 * 
	 * @param model
	 */
	public MusicTable(final GuiController controller) {
		super(controller.getModel());

		// Double click to inspect
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
					controller.inspectMusicFile();
				} else if (SwingUtilities.isRightMouseButton(e)) {
					JPopupMenu menu = new ContextualMenu(controller);
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		// Column 3 special 3 states renderer
		setDefaultRenderer(Boolean.class, new ThreeStatesBooleanRenderer());
		setDefaultRenderer(IMusicFile.class, new MusicFileRenderer());

		// Column size
		getColumn(0).setMaxWidth(200);
		getColumn(2).setMaxWidth(70);
	}
}
