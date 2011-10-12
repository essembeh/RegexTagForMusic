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
package org.essembeh.rtfm.gui.action;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.utils.Image;

public class ReadDBAction extends GenericAction {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1517407682888362629L;

	/**
	 * Constructor
	 * 
	 * @param controller
	 * @throws ConfigurationException
	 */
	public ReadDBAction(RTFMController controller) throws ConfigurationException {
		super(controller, "string.gui.read_db", Image.FILE_OPEN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Create a file chooser to select an XML File
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// TODO: Filter only Database files (XML files)
		fileChooser.setDialogTitle("Select the RTFM Database File");
		fileChooser.setCurrentDirectory(new File("."));
		int rc = fileChooser.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION) {
			this.controller.doReadDatabase(fileChooser.getSelectedFile());
		}
	}
}
