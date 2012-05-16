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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.IJob;
import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.core.configuration.ActionService;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.filter.Filter;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.gui.dialog.FileInspectorDialog;
import org.essembeh.rtfm.gui.dialog.JobDialog;
import org.essembeh.rtfm.gui.model.MusicTableModel;
import org.essembeh.rtfm.gui.panel.MainPanel;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;

import com.google.inject.Inject;

public class GuiController {

	private static final Logger logger = Logger.getLogger(GuiController.class);

	private final Library model;

	private final MusicTableModel tableModel;

	private final MainPanel view;

	private File currentDatabase;

	private final ActionService actionService;

	@Inject
	public GuiController(Library model, RTFMProperties properties, ActionService actionService) {
		this.model = model;
		this.actionService = actionService;
		this.tableModel = new MusicTableModel(model);
		this.view = new MainPanel(this, properties);
		currentDatabase = null;
		actionService = null;
	}

	public void displayStatusMessage(String message, boolean isAnError) {
		logger.debug("Display message: " + message);
		if (isAnError) {
			this.view.statusPrintError(message);
		} else {
			this.view.statusPrintMessage(message);
		}
	}

	public void doExecuteActionForAll(IWorkflowIdentifier actionName) {
		// Job already running
		// JOptionPane.showMessageDialog(this.getMainPanel(),
		// Translator.get(StringId.messageJobAlreadyRunning),
		// "Warning", JOptionPane.INFORMATION_MESSAGE);
		final List<IMusicFile> list = view.getAllFiles();

		try {
			IJob job = actionService.createJob(actionName, list);
			JobDialog jobDialog = new JobDialog(job);
			jobDialog.setVisible(true);
			// job.addListener(new IJobListener() {
			// private int error = 0;
			//
			// @Override
			// public void succeeded(Workflow workflow, IMusicFile musicFile) {
			// }
			//
			// @Override
			// public void start(Workflow workflow) {
			// }
			//
			// @Override
			// public void process(Workflow workflow, IMusicFile musicFile) {
			// }
			//
			// @Override
			// public void error(Workflow workflow, IMusicFile musicFile,
			// ActionException e) {
			// error++;
			// }
			//
			// @Override
			// public void end(Workflow workflow) {
			// String message = "Action \"" + workflow.getIdentifier() +
			// "\" executed on "
			// + TextUtils.plural(list.size(), "file");
			// if (error == 0) {
			// displayStatusMessage(message, false);
			// } else {
			// String errorMessage = " with " + TextUtils.plural(error,
			// "error");
			// displayStatusMessage(message + errorMessage, true);
			// }
			// updateCurrentTab();
			//
			// }
			// });
			// job.submit();
		} catch (ActionException e1) {
			logger.error("Error", e1);
			e1.printStackTrace();
		}
	}

	public void doReadDatabase(File databaseFile) {
		try {
			this.model.loadFrom(databaseFile);
			displayStatusMessage(Translator.get(StringId.statusDatabaseRead) + databaseFile.getAbsolutePath(), false);
			// save current file to propose when saving
			this.currentDatabase = databaseFile;
		} catch (Exception e) {
			displayStatusMessage(e.getMessage(), true);
			e.printStackTrace();
		}
		updateLibraryInfo();
		updateAfterTabChange();
	}

	public void doScanFolder(File folder) {
		this.currentDatabase = null;
		try {
			this.model.scanFolder(folder);
			displayStatusMessage(Translator.get(StringId.statusFolderScanned)
					+ this.model.getRootFolder().getAbsolutePath(), false);
		} catch (Exception e) {
			displayStatusMessage(e.getMessage(), true);
			e.printStackTrace();
		}
		updateLibraryInfo();
		updateAfterTabChange();
	}

	public void doWriteDatabase(File file) {
		try {
			this.model.writeTo(file);
			displayStatusMessage(Translator.get(StringId.statusDatabaseSaved) + file.getAbsolutePath(), false);
		} catch (LibraryException e) {
			displayStatusMessage(e.getMessage(), true);
			e.printStackTrace();
		}

	}

	public File getCurrentDatabase() {
		return this.currentDatabase;
	}

	public Filter getCurrentFilter() {
		return this.view.getCurrentFilter();
	}

	public JPanel getMainPanel() {
		return this.view;
	}

	public MusicTableModel getModel() {
		return this.tableModel;
	}

	public List<String> getModelTypes() {
		List<String> list = new ArrayList<String>();
		for (FileType ft : tableModel.getTypeList()) {
			list.add(ft.getIdentifier());
		}
		return list;
	}

	public void inspectMusicFile() {
		List<IMusicFile> file = this.view.getCurrentSelectionOfFiles();
		for (IMusicFile musicFile : file) {
			FileInspectorDialog dialog = new FileInspectorDialog(musicFile);
			dialog.setVisible(true);
		}
	}

	public void updateActions() {
		Map<IWorkflowIdentifier, Integer> map = new HashMap<IWorkflowIdentifier, Integer>();
		for (IMusicFile musicFile : view.getAllFiles()) {
			for (IWorkflowIdentifier action : actionService.getWorkflowsForType(musicFile.getType())) {
				int newCount = 0;
				if (map.containsKey(action)) {
					newCount = map.get(action);
				}
				map.put(action, ++newCount);
			}
		}
		this.view.updateActions(map);
	}

	public void updateLibraryInfo() {
		this.view.updateInformationPanel();
	}

	public void updateCurrentTab() {
		Filter currentFilter = this.view.getCurrentFilter();
		this.tableModel.updateWithFilter(currentFilter);
	}

	public void updateAfterTabChange() {
		updateCurrentTab();
		this.view.setTypeList(getModelTypes());
		updateActions();
	}
}
