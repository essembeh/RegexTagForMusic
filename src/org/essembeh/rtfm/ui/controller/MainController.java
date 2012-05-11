package org.essembeh.rtfm.ui.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.table.TableModel;

import org.essembeh.rtfm.core.configuration.ActionService;
import org.essembeh.rtfm.core.exception.LibraryException;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;
import org.essembeh.rtfm.ui.model.AttributesModel;
import org.essembeh.rtfm.ui.model.MusicFileModel;

import com.google.inject.Inject;

public class MainController {

	private final Library library;

	private final ActionService actionService;

	private final MusicFileModel musicFileModel;
	private final AttributesModel attributesModel;

	private File currentDatabase;

	@Inject
	public MainController(Library library, ActionService actionService) {
		this.library = library;
		this.actionService = actionService;
		this.musicFileModel = new MusicFileModel(library);
		this.attributesModel = new AttributesModel();
	}

	public TableModel getFileModel() {
		return musicFileModel;
	}

	public TableModel getAttributesModel() {
		return attributesModel;
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
				musicFileModel.fireTableDataChanged();
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
				musicFileModel.fireTableDataChanged();
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

	public void updateSelection(int[] selectedRows) {
		List<IMusicFile> selection = new ArrayList<IMusicFile>();
		for (int i = 0; i < selectedRows.length; i++) {
			selection.add(musicFileModel.getMusicFileAtRow(selectedRows[i]));
		}
		attributesModel.updateSelection(selection);
	}

}
