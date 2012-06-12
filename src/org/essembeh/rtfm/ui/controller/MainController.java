package org.essembeh.rtfm.ui.controller;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.essembeh.rtfm.core.actions.IJob;
import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.utils.TextUtils;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;
import org.essembeh.rtfm.ui.dialog.JobDialogCustom;
import org.essembeh.rtfm.ui.model.AttributesModel;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils;
import org.essembeh.rtfm.ui.model.FiltersModel;
import org.essembeh.rtfm.ui.model.FiltersSelection;
import org.essembeh.rtfm.ui.model.MusicFilesModel;
import org.essembeh.rtfm.ui.model.MusicFilesSelection;
import org.essembeh.rtfm.ui.model.WorkflowModel;
import org.essembeh.rtfm.ui.panel.StatusBar;

import com.google.inject.Inject;

public class MainController {

	private final Library library;

	private final FiltersModel filtersModel;
	private final FiltersSelection filtersSelection;
	private final MusicFilesModel musicFilesModel;
	private final MusicFilesSelection musicFilesSelection;
	private final AttributesModel attributesModel;
	private final WorkflowModel workflowModel;
	private final StatusBar statusBar;

	private File currentDatabase;

	@Inject
	public MainController(Library library) {
		this.library = library;
		this.filtersModel = new FiltersModel(library, new ExplorerNodeUtils(library), true);
		this.filtersSelection = new FiltersSelection();

		this.musicFilesModel = new MusicFilesModel(library, filtersSelection);
		this.musicFilesSelection = new MusicFilesSelection(musicFilesModel);

		this.attributesModel = new AttributesModel(musicFilesModel, musicFilesSelection);
		this.workflowModel = new WorkflowModel(library, musicFilesModel, musicFilesSelection);

		this.statusBar = new StatusBar();

		// Listener to display selection count in statusbar
		musicFilesSelection.addListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				int count = musicFilesSelection.getSelectedFiles().size();
				if (count > 0) {
					statusBar.printMessage(TextUtils.plural(count, "file") + " selected");
				} else {
					statusBar.hide();
				}
			}
		});
	}

	public FiltersModel getFiltersModel() {
		return filtersModel;
	}

	public FiltersSelection getFiltersSelection() {
		return filtersSelection;
	}

	public MusicFilesModel getMusicFilesModel() {
		return musicFilesModel;
	}

	public MusicFilesSelection getMusicFilesSelection() {
		return musicFilesSelection;
	}

	public AttributesModel getAttributesModel() {
		return attributesModel;
	}

	public WorkflowModel getWorkflowModel() {
		return workflowModel;
	}

	public void loadDatabase() {
		// Create a file chooser to select an XML File
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		// TODO: Filter only Database files (XML files)
		fileChooser.setDialogTitle(Translator.get(StringId.messageSelectDatabase));
		fileChooser.setCurrentDirectory(new File("."));
		int rc = fileChooser.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION) {
			currentDatabase = fileChooser.getSelectedFile();
			try {
				library.loadFrom(currentDatabase);
				String message = "Library loaded: " + currentDatabase.getName() + ", found "
						+ TextUtils.plural(library.getAllFiles().size(), "file");
				statusBar.printMessage(message);
			} catch (LibraryException e) {
				statusBar.printError(e);
			} catch (IOException e) {
				statusBar.printError(e);
			}
		}
	}

	public void scanFolder() {
		// Create a file chooser that only can select a folder
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle(Translator.get(StringId.messageSelectFolder));
		int rc = fileChooser.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION) {
			try {
				library.scanFolder(fileChooser.getSelectedFile());
				String message = "Folder scanned: " + library.getRootFolder().getAbsolutePath() + ", found "
						+ TextUtils.plural(library.getAllFiles().size(), "file");
				statusBar.printMessage(message);
			} catch (Exception e) {
				statusBar.printError(e);
			}
		}

	}

	public void saveDatabase() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogTitle(Translator.get(StringId.messageSelectDatabase));
		// Set File chooser default directory to current
		File currentDirectory = new File(".");
		// If a DB has been opened, set the chooser default directory to
		// the directory containing the database.
		if (currentDatabase != null) {
			currentDirectory = currentDatabase.getParentFile();
		}
		fileChooser.setCurrentDirectory(currentDirectory);
		int rc = fileChooser.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION) {
			currentDatabase = fileChooser.getSelectedFile();
			try {
				library.writeTo(currentDatabase);
				String message = "Library saved: " + currentDatabase.getName();
				statusBar.printMessage(message);
			} catch (LibraryException e) {
				statusBar.printError(e);
			}
		}
	}

	/**
	 * 
	 * @param workflowIdentifier
	 */
	public void executeWorkFlow(IWorkflowIdentifier workflowIdentifier) {
		List<IMusicFile> files = musicFilesSelection.getSelectedFiles();
		if (files.size() == 0) {
			files = musicFilesModel.getFilteredFiles();
		}
		try {
			IJob job = library.getActionService().createJob(workflowIdentifier, files);
			JobDialogCustom jobDialogCustom = new JobDialogCustom(job);
			jobDialogCustom.setVisible(true);
		} catch (ActionException e) {
			statusBar.printError(e);
		}
	}

	/**
	 * 
	 * @return
	 */
	public Component getStatusPanel() {
		return statusBar;
	}

}
