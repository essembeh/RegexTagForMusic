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

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker.StateValue;

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.DatabaseException;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.gui.dialog.FileInspectorDialog;
import org.essembeh.rtfm.gui.listener.TagJobListener;
import org.essembeh.rtfm.gui.model.MusicManagerModel;
import org.essembeh.rtfm.gui.panel.MainPanel;
import org.essembeh.rtfm.gui.worker.TagJob;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class RTFMController {

	/**
	 * The application
	 */
	protected MusicManager app = null;

	/**
	 * The model, used for table view
	 */
	protected MusicManagerModel model = null;

	/**
	 * The main panel
	 */
	protected MainPanel mainPanel = null;

	/**
	 * The thread used to tag
	 */
	protected TagJob tagWorker = null;

	/**
	 * The current database
	 */
	private File currentDatabase = null;

	/**
	 * Constructor
	 * 
	 * @param app
	 *            the application
	 * @throws ConfigurationException
	 */
	public RTFMController(MusicManager app) throws ConfigurationException {
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
			displayStatusMessage("Folder scanned: " + this.app.getRootFolder().getAbsolutePath(), false);
			this.currentDatabase = null;

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
			displayStatusMessage("Database read: " + databaseFile.getAbsolutePath(), false);
			// save current file to propose whe saving
			this.currentDatabase = databaseFile;
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
			displayStatusMessage("Database written: " + file.getAbsolutePath(), false);
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
	 * Updates information in the information panel with the current Application
	 * informations
	 */
	protected void updateInformationPanel() {
		this.mainPanel.updateInformationPanel(this.app);
		this.mainPanel.setTypeList(getModelTypes());
	}

	/**
	 * Tags all given files
	 * 
	 * @param list
	 */
	protected void tagListOfFiles(List<IMusicFile> list) {
		if (this.tagWorker != null && this.tagWorker.getState() != StateValue.DONE) {
			// Job already running
			JOptionPane.showMessageDialog(this.getMainPanel(), "A job is already running", "Warning",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			this.tagWorker = new TagJob(list, this.mainPanel.getStatusBar());
			this.tagWorker
					.addPropertyChangeListener(new TagJobListener(this.mainPanel.getStatusBar().getProgressBar()));
			this.tagWorker.execute();
		}
	}

	/**
	 * Displays a message in the status bar of the panel
	 * 
	 * @param message
	 * @param isAnError
	 */
	public void displayStatusMessage(String message, boolean isAnError) {
		if (isAnError) {
			this.mainPanel.statusPrintError(message);
		} else {
			this.mainPanel.statusPrintMessage(message);
		}
	}

	/**
	 * Opens a window to display informations about the selected MusicFiles.
	 */
	public void inspectMusicFile() {
		List<IMusicFile> file = this.mainPanel.getCurrentSelectionOfFiles();
		for (IMusicFile iMusicFile : file) {
			try {
				TagData tagData = null;
				if (iMusicFile.isTaggable()) {
					tagData = iMusicFile.getTagData();
				}
				FileInspectorDialog dialog = new FileInspectorDialog(iMusicFile, tagData);
				dialog.setVisible(true);
			} catch (Exception e) {
				displayStatusMessage(e.getMessage(), true);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the last opened Database. Null if a root folder has be scanned.
	 * 
	 * @return the currentDatabase
	 */
	public File getCurrentDatabase() {
		return this.currentDatabase;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getModelTypes() {
		return this.model.getTypeList();
	}
}
