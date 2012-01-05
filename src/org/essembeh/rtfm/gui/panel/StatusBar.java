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
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.essembeh.rtfm.gui.worker.StatusBarCleaner;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 2713289246117490437L;

	JLabel statusMessage = null;
	JProgressBar progressBar = null;
	StatusBarCleaner cleaner = null;

	public StatusBar(String welcomeMessage) {
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridLayout(1, 1));
		this.statusMessage = new JLabel();
		this.progressBar = new JProgressBar(0, 100);
		this.progressBar.setValue(100);
		this.progressBar.setStringPainted(true);
		this.statusMessage.setFont(new Font(Font.MONOSPACED, Font.ITALIC, this.statusMessage.getFont().getSize() - 1));
		printMessage(welcomeMessage);
	}

	protected void setModeMessage() {
		removeAll();
		add(this.statusMessage);
		updateUI();
	}

	protected void setModeProgressBar() {
		removeAll();
		this.progressBar.setValue(0);
		add(this.progressBar);
		updateUI();
	}

	protected void setModeHidden() {
		removeAll();
		updateUI();
	}

	public void printMessage(String line) {
		if (line != null) {
			setModeMessage();
			this.statusMessage.setForeground(Color.BLUE);
			this.statusMessage.setText(line);
			autoClear(5);
		}
	}

	public void printError(String line) {
		setModeMessage();
		this.statusMessage.setForeground(Color.RED);
		this.statusMessage.setText(line);
		autoClear(20);
	}

	protected void autoClear(int timeout) {
		if (this.cleaner != null) {
			this.cleaner.cancel(true);
		}
		this.cleaner = new StatusBarCleaner(this, timeout);
		this.cleaner.execute();
	}

	public JProgressBar getProgressBar() {
		setModeProgressBar();
		return this.progressBar;
	}

	public void clear() {
		setModeHidden();
		this.statusMessage.setText("");
		// Set the progress bar if not 100%
		if (this.progressBar.getValue() < 100) {
			setModeProgressBar();
		}
	}
}
