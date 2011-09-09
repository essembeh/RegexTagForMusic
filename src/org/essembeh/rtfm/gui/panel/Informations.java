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

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.gui.utils.SpringUtilities;
import org.jdesktop.swingx.JXTaskPane;

public class Informations extends JXTaskPane {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 5764326428888520100L;

	/**
	 * Constructor
	 */
	public Informations() {
		super("Informations");
	}

	/**
	 * Updates the informations
	 * 
	 * @param app
	 */
	public void update(MusicManager app) {
		removeAll();
		if (app != null && app.getRootFolder() != null) {
			JPanel panel = new JPanel();
			panel.setLayout(new SpringLayout());
			add(panel);
			String rootFolder = app.getRootFolder().getName();
			int totalCount = app.getAllFiles().size();
			int nonTaggedCount = app.getFilteredFiles(Filter.NON_TAGGED).size();

			addRow(panel, "Music folder", rootFolder);
			addRow(panel, "File count", "" + totalCount);
			addRow(panel, "Non tagged", "" + nonTaggedCount);
			// Lay out the panel.
			SpringUtilities.makeCompactGrid(panel, // parent
					3, 2, // rows, cols
					1, 1, // initX, initY
					1, 1); // xPad, yPad
		}

	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	protected void addRow(JPanel panel, String name, String value) {
		panel.add(new JLabel(name + ": ", JLabel.TRAILING));
		panel.add(new JLabel(value));
	}
}
