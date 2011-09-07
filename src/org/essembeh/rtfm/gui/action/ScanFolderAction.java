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

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.utils.Image;
import org.essembeh.rtfm.gui.utils.ImageUtils;

public class ScanFolderAction extends AbstractAction {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 8763175742264301763L;

	/**
	 * The controller
	 */
	protected RTFMController controller;

	/**
	 * Constructor
	 * 
	 * @param controller
	 */
	public ScanFolderAction(RTFMController controller) {
		this.controller = controller;
		putValue(NAME, "New");
		try {
			putValue(SMALL_ICON, new ImageUtils(Image.FILE_NEW).getIcon());
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// Create a file chooser that only can select a folder
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle("Select the folder containing your Music");
		int rc = fileChooser.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION) {
			this.controller.doScanFolder(fileChooser.getSelectedFile());
		}
	}
}
