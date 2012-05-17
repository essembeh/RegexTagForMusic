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
	private volatile JLabel statusMessage;

	public StatusBar(String welcomeMessage) {
		// setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BorderLayout());
		this.statusMessage = new JLabel();
		this.statusMessage.setFont(new Font(Font.MONOSPACED, Font.ITALIC, this.statusMessage.getFont().getSize() - 1));
		add(statusMessage, BorderLayout.NORTH);
		printMessage(welcomeMessage);
	}

	public void printMessage(String line) {
		if (line != null) {
			this.statusMessage.setForeground(Color.BLUE);
			this.statusMessage.setText(line);
		}
	}

	public void printError(String line) {
		this.statusMessage.setForeground(Color.RED);
		this.statusMessage.setText(line);
	}

	public void printError(Exception e) {
		printMessage(e.getMessage());
	}
}
