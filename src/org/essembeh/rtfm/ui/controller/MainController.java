package org.essembeh.rtfm.ui.controller;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;
import org.essembeh.rtfm.ui.model.AttributesModel;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils;
import org.essembeh.rtfm.ui.model.FiltersModel;
import org.essembeh.rtfm.ui.model.FiltersSelection;
import org.essembeh.rtfm.ui.model.MusicFilesModel;
import org.essembeh.rtfm.ui.model.MusicFilesSelection;
import org.essembeh.rtfm.ui.model.WorkflowModel;

import com.google.inject.Inject;

public class MainController {

	private final Library library;

	private final FiltersModel filtersModel;
	private final FiltersSelection filtersSelection;
	private final MusicFilesModel musicFilesModel;
	private final MusicFilesSelection musicFilesSelection;
	private final AttributesModel attributesModel;
	private final WorkflowModel workflowModel;

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
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY | JFileChooser.OPEN_DIALOG);
		// TODO: Filter only Database files (XML files)
		fileChooser.setDialogTitle(Translator.get(StringId.messageSelectDatabase));
		fileChooser.setCurrentDirectory(new File("."));
		int rc = fileChooser.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION) {
			currentDatabase = fileChooser.getSelectedFile();
			try {
				library.loadFrom(currentDatabase);
			} catch (LibraryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void scanFolder() {
		// Create a file chooser that only can select a folder
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY | JFileChooser.OPEN_DIALOG);
		fileChooser.setDialogTitle(Translator.get(StringId.messageSelectFolder));
		fileChooser.setCurrentDirectory(new File("."));
		int rc = fileChooser.showOpenDialog(null);
		if (rc == JFileChooser.APPROVE_OPTION) {
			try {
				library.scanFolder(fileChooser.getSelectedFile());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void saveDatabase() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY | JFileChooser.SAVE_DIALOG);
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
			} catch (LibraryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param workflowIdentifier
	 */
	public void executeWorkFlow(IWorkflowIdentifier workflowIdentifier) {
		System.err.println(workflowIdentifier.getIdentifier());
	}

}
