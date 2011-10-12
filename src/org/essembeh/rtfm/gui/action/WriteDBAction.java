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

public class WriteDBAction extends GenericAction {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -8442180040448886826L;

	/**
	 * Constructor
	 * 
	 * @param controller
	 * @throws ConfigurationException
	 */
	public WriteDBAction(RTFMController controller) throws ConfigurationException {
		super(controller, "string.gui.write_db", Image.FILE_SAVE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogTitle("Select the Database File");
		// Set File chooser default directory to current
		File currentDirectory = new File(".");
		// If a DB has been opened, set the chooser default directory to
		// the directory containing the database.
		File currentDB = this.controller.getCurrentDatabase();
		if (currentDB != null) {
			currentDirectory = currentDB.getParentFile();
		}
		fileChooser.setCurrentDirectory(currentDirectory);
		int rc = fileChooser.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION) {
			this.controller.doWriteDatabase(fileChooser.getSelectedFile());
		}
	}
}
