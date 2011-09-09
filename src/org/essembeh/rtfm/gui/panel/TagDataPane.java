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
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.gui.utils.SpringUtilities;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class TagDataPane extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 9011058431499845479L;

	protected int rowCount = 0;

	/**
	 * Constructor
	 * 
	 * @param file
	 * @param data
	 */
	public TagDataPane(final IMusicFile file, TagData data) {
		setLayout(new SpringLayout());
		addRow("Virtual path", file.getVirtualPath());
		addRow("Type", file.getType());

		if (data != null) {
			addRow("Artist", data.getArtist());
			addRow("Album", data.getAlbum());
			addRow("Year", data.getYear());
			addRow("Number", data.getTrackNumber());
			addRow("Title", data.getTrackName());
			addRow("Comment", data.getComment());
		}
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(this, // parent
				this.rowCount, 2, // rows, cols
				3, 3, // initX, initY
				3, 3); // xPad, yPad

	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	protected void addRow(String name, String value) {
		add(new JLabel(name + ": ", JLabel.TRAILING));
		JTextField text = new JTextField(value);
		add(text);
		this.rowCount++;
	}
}
