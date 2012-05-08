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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.essembeh.rtfm.core.filter.CommonFilters;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.gui.utils.SpringUtilities;
import org.jdesktop.swingx.JXTaskPane;

public class MusicManagerInformations extends JXTaskPane {

	private static final long serialVersionUID = 5764326428888520100L;
	private final Library library;

	public MusicManagerInformations(Library library) {
		super("Informations");
		this.library = library;
	}

	public void updateInformations() {
		removeAll();
		JPanel panel = new JPanel();
		panel.setLayout(new SpringLayout());
		add(panel);

		String path = "";
		if (library.getRootFolder() != null) {
			path = library.getRootFolder().getName();
		}
		addRow(panel, "Music folder", path);
		addRow(panel, "File count", "" + library.getAllFiles().size());
		addRow(panel, "Non tagged",
				"" + CommonFilters.filterOnAttribute("rtfm:tagged", "false").filter(library.getAllFiles()).size());
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(panel, // parent
				3, 2, // rows, cols
				1, 1, // initX, initY
				1, 1); // xPad, yPad
	}

	protected void addRow(JPanel panel, String name, String value) {
		panel.add(new JLabel(name + ": ", SwingConstants.TRAILING));
		panel.add(new JLabel(value));
	}
}
