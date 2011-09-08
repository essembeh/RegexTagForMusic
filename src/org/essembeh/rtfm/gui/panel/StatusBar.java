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

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.essembeh.rtfm.core.conf.RTFMProperties;
import org.essembeh.rtfm.gui.worker.JLabelDelayedMessage;
import org.jdesktop.swingx.HorizontalLayout;

public class StatusBar extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 2713289246117490437L;

	/**
	 * The status line
	 */
	protected JLabel statusLine = null;

	/**
	 * The cleaner
	 */
	private JLabelDelayedMessage cleaner = null;

	/**
	 * Constructor
	 */
	public StatusBar() {

		setBackground(Color.LIGHT_GRAY);
		setLayout(new HorizontalLayout());
		this.statusLine = new JLabel();
		this.statusLine.setFont(new Font(Font.MONOSPACED, Font.ITALIC,
				this.statusLine.getFont().getSize() - 1));
		add(this.statusLine);
		printMessage(RTFMProperties.getProperty("app.name") + " "
				+ RTFMProperties.getProperty("app.version"));
	}

	/**
	 * Set the text of the status bar.
	 * 
	 * @param line
	 */
	public void printMessage(String line) {
		this.statusLine.setForeground(Color.BLUE);
		this.statusLine.setText(line);
		autoClear(5);
	}

	/**
	 * Set the text of the status bar.
	 * 
	 * @param line
	 */
	public void printError(String line) {
		this.statusLine.setForeground(Color.RED);
		this.statusLine.setText(line);
		autoClear(20);
	}

	/**
	 * Clean the label after a timeout
	 * 
	 * @param timeout
	 */
	protected void autoClear(int timeout) {
		if (this.cleaner != null) {
			this.cleaner.cancel(true);
		}
		this.cleaner = new JLabelDelayedMessage(this.statusLine, timeout, "");
		cleaner.execute();
	}
}
