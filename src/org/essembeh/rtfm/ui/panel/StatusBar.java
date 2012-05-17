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
package org.essembeh.rtfm.ui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 2713289246117490437L;
	private final JLabel shortMessage;

	public StatusBar() {
		// setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLoweredBevelBorder());
		setLayout(new BorderLayout());

		// Short message
		this.shortMessage = new JLabel();
		this.shortMessage.setFont(new Font(Font.MONOSPACED, Font.BOLD, this.shortMessage.getFont().getSize() - 1));
		add(shortMessage, BorderLayout.NORTH);
	}

	public void printMessage(String line) {
		shortMessage.setForeground(Color.BLACK);
		shortMessage.setText(line);
		shortMessage.setVisible(true);
	}

	public void printError(String line) {
		shortMessage.setForeground(Color.RED);
		shortMessage.setText(line);
		shortMessage.setVisible(true);
	}

	public void printError(Exception e) {
		printError(e.getMessage());
	}

	public void hide() {
		shortMessage.setText("");
		shortMessage.setVisible(false);
	}
}
