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
package org.essembeh.rtfm.gui.controller;

import java.io.File;
import java.util.List;

import javax.swing.JPanel;

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.exception.DatabaseException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.gui.dialog.TagDataDialog;
import org.essembeh.rtfm.gui.model.MusicManagerModel;
import org.essembeh.rtfm.gui.panel.MainPanel;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class RTFMController {

	/**
	 * The application
	 */
	protected MusicManager app;

	/**
	 * The model, used for table view
	 */
	protected MusicManagerModel model;

	/**
	 * The main panel
	 */
	protected MainPanel mainPanel;

	/**
	 * Constructor
	 * 
	 * @param app
	 *            the application
	 */
	public RTFMController(MusicManager app) {
		this.app = app;
		this.model = new MusicManagerModel(app);
		this.mainPanel = new MainPanel(this);
	}

	/**
	 * Scans the given folder and refresh the view
	 * 
	 * @param folder
	 *            the folder to scan
	 */
	public void doScanFolder(File folder) {
		try {
			this.app.scanMusicFolder(folder);
			this.model.updateWithFilter(null);
			displayStatusMessage("Folder scanned: "
					+ this.app.getRootFolder().getAbsolutePath(), false);

		} catch (Exception e) {
			displayStatusMessage(e.getMessage(), true);
			e.printStackTrace();
		}
		updateInformationPanel();
	}

	/**
	 * Reads the given database and refresh the view
	 * 
	 * @param databaseFile
	 *            the database
	 */
	public void doReadDatabase(File databaseFile) {
		try {
			this.app.readDatabase(databaseFile, true);
			this.model.updateWithFilter(null);
			displayStatusMessage("Database read: "
					+ databaseFile.getAbsolutePath(), false);
		} catch (Exception e) {
			displayStatusMessage(e.getMessage(), true);
			e.printStackTrace();
		}
		updateInformationPanel();
	}

	/**
	 * Writes the database in the given file
	 * 
	 * @param file
	 *            path to the database
	 */
	public void doWriteDatabase(File file) {
		try {
			this.app.writeDatabase(file);
			displayStatusMessage("Database written: " + file.getAbsolutePath(),
					false);
		} catch (DatabaseException e) {
			displayStatusMessage(e.getMessage(), true);
			e.printStackTrace();
		}

	}

	/**
	 * Tags all file in the current tab
	 */
	public void doTagAllFiles() {
		List<IMusicFile> list = this.mainPanel.getAllFiles();
		tagListOfFiles(list);
	}

	/**
	 * Tags files that are selected in the current tab
	 */
	public void doTagSelectedFiles() {
		List<IMusicFile> list = this.mainPanel.getCurrentSelectionOfFiles();
		tagListOfFiles(list);
	}

	/**
	 * Updates the model and the view with the filter in the current tab
	 */
	public void updateFilter() {
		Filter currentFilter = this.mainPanel.getCurrentFilter();
		this.model.updateWithFilter(currentFilter);

	}

	/**
	 * Returns the main panel
	 * 
	 * @return the panel
	 */
	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	/**
	 * Returns the model
	 * 
	 * @return the model
	 */
	public MusicManagerModel getModel() {
		return this.model;
	}

	/**
	 * Update information in the information panel with the current Application
	 * informations
	 */
	protected void updateInformationPanel() {
		this.mainPanel.updateInformationPanel(this.app);
	}

	/**
	 * Tags all given files
	 * 
	 * @param list
	 */
	protected void tagListOfFiles(List<IMusicFile> list) {
		int totalCount = list.size();
		int errorCount = 0;
		for (IMusicFile file : list) {
			if (file.isTaggable()) {
				try {
					file.tag(false);
					displayStatusMessage("Tagging: " + file.getVirtualPath(),
							false);
				} catch (Exception e) {
					errorCount++;
					e.printStackTrace();
				}
			}
		}
		if (errorCount == 0) {
			displayStatusMessage(totalCount
					+ StringUtils.plural(" file", totalCount) + " tagged",
					false);
		} else {
			displayStatusMessage(totalCount
					+ StringUtils.plural(" file", totalCount) + " tagged with "
					+ errorCount + StringUtils.plural(" error", errorCount),
					true);

		}
	}

	/**
	 * Display a message in the status bar of the panel
	 * 
	 * @param message
	 * @param isAnError
	 */
	protected void displayStatusMessage(String message, boolean isAnError) {
		if (isAnError) {
			this.mainPanel.statusPrintError(message);
		} else {
			this.mainPanel.statusPrintMessage(message);
		}
	}

	/**
	 * 
	 */
	public void inspectMusicFile() {
		List<IMusicFile> file = this.mainPanel.getCurrentSelectionOfFiles();
		for (IMusicFile iMusicFile : file) {
			try {
				TagData tagData = null;
				if (iMusicFile.isTaggable()) {
					tagData = iMusicFile.getTagData();
				}
				TagDataDialog dialog = new TagDataDialog(iMusicFile, tagData);
				dialog.setVisible(true);
			} catch (Exception e) {
				displayStatusMessage(e.getMessage(), true);
				e.printStackTrace();
			}
		}
	}
}
