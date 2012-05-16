package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class WorkflowModel extends DefaultComboBoxModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7840068219637080176L;
	private final String firstElement;
	private final Library library;
	private final MusicFilesModel musicFilesModel;
	private final MusicFilesSelection musicFilesSelection;
	private final List<IWorkflowIdentifier> workflows;

	public WorkflowModel(Library library, MusicFilesModel musicFilesModel, MusicFilesSelection musicFilesSelection) {
		super();
		this.library = library;
		this.musicFilesModel = musicFilesModel;
		this.musicFilesSelection = musicFilesSelection;
		this.workflows = new ArrayList<IWorkflowIdentifier>();
		this.firstElement = "Choose an action";
		// Setup listeners
		musicFilesModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				refresh();
			}
		});
		musicFilesSelection.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
			}
		});
	}

	protected void refresh() {
		List<IMusicFile> files = musicFilesSelection.getSelectedFiles();
		if (files.size() == 0) {
			files = musicFilesModel.getFilteredFiles();
		}
		workflows.clear();
		for (IMusicFile musicFile : files) {
			for (IWorkflowIdentifier workflowIdentifier : library.getActionService().getWorkflowsForType(musicFile.getType())) {
				if (!workflows.contains(workflowIdentifier)) {
					workflows.add(workflowIdentifier);
				}
			}
		}
		fireContentsChanged(this, 0, getSize());
		setSelectedItem(firstElement);
	}

	@Override
	public int getSize() {
		return workflows.size() + 1;
	}

	@Override
	public Object getElementAt(int index) {
		return index == 0 ? firstElement : workflows.get(index - 1);
	}

}
