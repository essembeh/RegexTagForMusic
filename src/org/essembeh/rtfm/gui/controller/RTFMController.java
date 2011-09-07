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
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.DatabaseException;
import org.essembeh.rtfm.core.exception.RTFMException;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.core.exception.TagWriterException;
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
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (RTFMException e) {
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
		} catch (DatabaseException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (RTFMException e) {
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
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tags all file in the current tab
	 */
	public void doTagAllFiles() {
		System.out.println("Tag ...");
		List<IMusicFile> list = this.mainPanel.getAllFiles();
		tagListOfFiles(list);
	}

	/**
	 * Tags files that are selected in the current tab
	 */
	public void doTagSelectedFiles() {
		System.out.println("Tag ...");
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
		for (IMusicFile file : list) {
			if (file.isTaggable()) {
				try {
					file.tag(false);
				} catch (TagNotFoundException e) {
					e.printStackTrace();
				} catch (RTFMException e) {
					e.printStackTrace();
				} catch (TagWriterException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
