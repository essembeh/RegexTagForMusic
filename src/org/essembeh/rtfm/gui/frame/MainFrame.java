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
package org.essembeh.rtfm.gui.frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 8483703940834939005L;

	/**
	 * Constructor
	 * 
	 * @param panel
	 */
	public MainFrame(JPanel panel) {
		super("RegexTagForMusic");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
	}
}
