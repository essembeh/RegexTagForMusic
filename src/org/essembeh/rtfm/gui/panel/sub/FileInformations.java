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
package org.essembeh.rtfm.gui.panel.sub;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.gui.utils.SpringUtilities;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;
import org.jdesktop.swingx.VerticalLayout;

public class FileInformations extends JPanel {

	private static final long serialVersionUID = 9011058431499845479L;

	public FileInformations(final MusicFile file) {

		// Top Panel
		JPanel top = new JPanel();
		top.setLayout(new SpringLayout());
		// Path
		top.add(createLabel(Translator.get(StringId.columnPath)));
		top.add(createTextField(file.getVirtualPath()));
		// Type
		top.add(createLabel(Translator.get(StringId.columnType)));
		top.add(createTextField(file.getType()));
		SpringUtilities.makeCompactGrid(top, 2, 2, 3, 3, 3, 3);

		// Bottom
		JPanel bottom = new JPanel();
		bottom.setLayout(new SpringLayout());
		bottom.setBorder(BorderFactory.createTitledBorder("Attributes"));
		// foreach attribute
		for (Attribute attribute : file.getAttributeList()) {
			bottom.add(createLabel(attribute.isHidden() ? "-" : "+" + attribute.getName()));
			bottom.add(createTextField(attribute.getValue()));
		}
		// Lay out the panel.
		SpringUtilities.makeCompactGrid(bottom, file.getAttributeList().size(), 2, 3, 3, 3, 3);

		setLayout(new VerticalLayout());
		add(top);
		add(bottom);
	}

	JLabel createLabel(String name) {
		JLabel jLabel = new JLabel(name + ": ", SwingConstants.TRAILING);
		return jLabel;
	}

	JTextField createTextField(String value) {
		JTextField text = new JTextField(value);
		text.setEditable(false);
		return text;
	}
}
