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

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JPanel;

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class MainPanel extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -8753385764638412756L;

	/**
	 * UI Elements
	 */
	protected SidePanel sidePanel;
	protected TabManager tabManager;
	protected StatusBar statusBar;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            the controller
	 */
	public MainPanel(RTFMController controller) {
		setLayout(new BorderLayout());
		this.sidePanel = new SidePanel(controller);
		this.tabManager = new TabManager(controller);
		this.statusBar = new StatusBar();
		add(this.sidePanel, BorderLayout.EAST);
		add(this.tabManager, BorderLayout.CENTER);
		add(this.statusBar, BorderLayout.SOUTH);
	}

	/**
	 * Returns the filter of the current tab
	 * 
	 * @return the filter
	 */
	public Filter getCurrentFilter() {
		return this.tabManager.getCurrentFilter();
	}

	/**
	 * Returns all files of the current tab
	 * 
	 * @return the list of files
	 */
	public List<IMusicFile> getAllFiles() {
		return this.tabManager.getAllFiles();
	}

	/**
	 * Returns the list of selected files in the current tab
	 * 
	 * @return the list of files
	 */
	public List<IMusicFile> getCurrentSelectionOfFiles() {
		return this.tabManager.getCurrentSelectionOfFiles();
	}

	/**
	 * Updates the informations
	 * 
	 * @param app
	 */
	public void updateInformationPanel(MusicManager app) {
		this.sidePanel.updateInformationPanel(app);
	}

	/**
	 * Set the text of the status bar.
	 * 
	 * @param line
	 */
	public void statusPrintMessage(String line) {
		this.statusBar.printMessage(line);
	}

	/**
	 * Set the text of the status bar.
	 * 
	 * @param line
	 */
	public void statusPrintError(String line) {
		this.statusBar.printError(line);
	}
}
