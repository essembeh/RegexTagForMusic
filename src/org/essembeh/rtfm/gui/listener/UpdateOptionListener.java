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
package org.essembeh.rtfm.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import org.essembeh.rtfm.gui.controller.RTFMController;

public class UpdateOptionListener implements ActionListener {

	/**
	 * Attributes
	 */
	protected String property;
	protected RTFMController controller;

	/**
	 * Constructor
	 * 
	 * @param controller
	 * @param checkBox
	 * @param property
	 */
	public UpdateOptionListener(RTFMController controller, String property) {
		this.controller = controller;
		this.property = property;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox checkBox = (JCheckBox) e.getSource();
		Boolean value = checkBox.isSelected();
		this.controller.updateProperty(this.property, value.toString());
	}
}
