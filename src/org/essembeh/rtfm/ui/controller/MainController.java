package org.essembeh.rtfm.ui.controller;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.essembeh.rtfm.core.actions.IJob;
import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.core.configuration.ConfigurationHelper;
import org.essembeh.rtfm.core.configuration.CoreConfigurationServices;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.utils.TextUtils;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;
import org.essembeh.rtfm.ui.dialog.JobDialogCustom;
import org.essembeh.rtfm.ui.model.AttributesModel;
import org.essembeh.rtfm.ui.model.ConfigurationModel;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils;
import org.essembeh.rtfm.ui.model.FiltersModel;
import org.essembeh.rtfm.ui.model.FiltersSelection;
import org.essembeh.rtfm.ui.model.MusicFilesModel;
import org.essembeh.rtfm.ui.model.MusicFilesSelection;
import org.essembeh.rtfm.ui.model.WorkflowModel;
import org.essembeh.rtfm.ui.panel.StatusBar;
import org.essembeh.rtfm.ui.utils.Translator;
import org.essembeh.rtfm.ui.utils.Translator.StringId;

import com.google.inject.Inject;

public class MainController {

	/**
	 * 
	 */
	private final Library library;
	private final CoreConfigurationServices configurationServices;
	private final ConfigurationHelper configurationHelper;
	private final FiltersModel filtersModel;
	private final FiltersSelection filtersSelection;
	private final MusicFilesModel musicFilesModel;
	private final MusicFilesSelection musicFilesSelection;
	private final AttributesModel attributesModel;
	private final WorkflowModel workflowModel;
	private final ConfigurationModel configurationModel;
	private final StatusBar statusBar;
	private File currentDatabase;

	/**
	 * 
	 * @param library
	 * @param configurationHelper
	 * @throws ConfigurationException
	 * @throws FileNotFoundException
	 */
	@Inject
	public MainController(Library library, CoreConfigurationServices configurationServices, ConfigurationHelper configurationHelper)
			throws ConfigurationException, FileNotFoundException {
		this.library = library;
		this.configurationServices = configurationServices;
		this.configurationHelper = configurationHelper;

		// Load default conf
		try {
			this.configurationServices.load(this.configurationHelper.getDefaultConfiguration());
		} catch (ReaderException e) {
			throw new ConfigurationException(e.getMessage());
		}

		// UI
		this.filtersModel = new FiltersModel(library, new ExplorerNodeUtils(library), true);
		this.filtersSelection = new FiltersSelection();

		this.musicFilesModel = new MusicFilesModel(library, filtersSelection);
		this.musicFilesSelection = new MusicFilesSelection(musicFilesModel);

		this.attributesModel = new AttributesModel(musicFilesModel, musicFilesSelection);
		this.workflowModel = new WorkflowModel(configurationServices, musicFilesModel, musicFilesSelection);
		this.configurationModel = new ConfigurationModel(configurationHelper);

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

	/**
	 * 
	 * @param workflowIdentifier
	 */
	public void executeWorkFlow(IWorkflowIdentifier workflowIdentifier) {
		List<IXFile> files = musicFilesSelection.getSelectedFiles();
		if (files.size() == 0) {
			files = musicFilesModel.getFilteredFiles();
		}
		try {
			IJob job = configurationServices.createJob(workflowIdentifier, files);
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
	public AttributesModel getAttributesModel() {
		return attributesModel;
	}

	/**
	 * 
	 * @return
	 */
	public ConfigurationModel getConfigurationModel() {
		return configurationModel;
	}

	/**
	 * 
	 * @return
	 */
	public FiltersModel getFiltersModel() {
		return filtersModel;
	}

	/**
	 * 
	 * @return
	 */
	public FiltersSelection getFiltersSelection() {
		return filtersSelection;
	}

	/**
	 * 
	 * @return
	 */
	public MusicFilesModel getMusicFilesModel() {
		return musicFilesModel;
	}

	/**
	 * 
	 * @return
	 */
	public MusicFilesSelection getMusicFilesSelection() {
		return musicFilesSelection;
	}

	/**
	 * 
	 * @return
	 */
	public Component getStatusPanel() {
		return statusBar;
	}

	/**
	 * 
	 * @return
	 */
	public WorkflowModel getWorkflowModel() {
		return workflowModel;
	}

	/**
	 * 
	 */
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
				library.load(new FileInputStream(currentDatabase));
				String message = "Library loaded: " + currentDatabase.getName() + ", found " + TextUtils.plural(library.getAllFiles().size(), "file");
				statusBar.printMessage(message);
			} catch (Exception e) {
				e.printStackTrace();
				statusBar.printError(e);
			}
		}
	}

	/**
	 * 
	 */
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
				library.save(new FileOutputStream(currentDatabase));
				String message = "Library saved: " + currentDatabase.getName();
				statusBar.printMessage(message);
			} catch (Exception e) {
				statusBar.printError(e);
			}
		}
	}

	/**
	 * 
	 */
	public void scanFolder() {
		// Create a file chooser that only can select a folder
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setDialogTitle(Translator.get(StringId.messageSelectFolder));
		if (currentDatabase != null) {
			fileChooser.setCurrentDirectory(currentDatabase.getParentFile());
		} else {
			fileChooser.setCurrentDirectory(new File("."));
		}
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

	/**
	 * 
	 * @param selection
	 */
	public void loadConfiguration(String selection) {
		try {
			library.resetValues();
			configurationServices.load(configurationHelper.getConfiguration(selection));
			statusBar.printMessage("Configuration loaded: " + selection);
		} catch (Exception e) {
			statusBar.printMessage("Error loading configuration: " + selection);
		}
	}
}
