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

import javax.swing.JPanel;

import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.jdesktop.swingx.VerticalLayout;

public class SidePanel extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -7647453688753906197L;

	/**
	 * The panel that displays informations
	 */
	protected MusicManagerInformations informations = null;

	/**
	 * Constructeur
	 * 
	 * @param controller
	 */
	public SidePanel(RTFMController controller) {
		setLayout(new VerticalLayout(10));
		this.informations = new MusicManagerInformations();
		add(new FileCommands(controller));
		add(this.informations);
		add(new TagCommands(controller));
	}

	/**
	 * Update the informations to display
	 * 
	 * @param app
	 */
	public void updateInformationPanel(MusicManager app) {
		this.informations.update(app);
	}
}
