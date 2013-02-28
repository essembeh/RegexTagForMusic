package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.workflow.ExecutionManager;
import org.essembeh.rtfm.core.workflow.Workflow;

public class WorkflowModel extends DefaultComboBoxModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7840068219637080176L;
	private final String firstElement;
	private final ExecutionManager executionManager;
	private final MusicFilesModel musicFilesModel;
	private final MusicFilesSelection musicFilesSelection;
	private final List<Workflow> workflows;

	/**
	 * 
	 * @param executionManager
	 * @param musicFilesModel
	 * @param musicFilesSelection
	 */
	public WorkflowModel(	ExecutionManager executionManager,
							MusicFilesModel musicFilesModel,
							MusicFilesSelection musicFilesSelection) {
		super();
		this.executionManager = executionManager;
		this.musicFilesModel = musicFilesModel;
		this.musicFilesSelection = musicFilesSelection;
		this.workflows = new ArrayList<Workflow>();
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

	/**
	 * 
	 */
	protected void refresh() {
		List<IXFile> files = musicFilesSelection.getSelectedFiles();
		if (files.size() == 0) {
			files = musicFilesModel.getFilteredFiles();
		}
		workflows.clear();
		for (Workflow workflowIdentifier : executionManager.getCompatibleWorkflows(files)) {
			if (!workflows.contains(workflowIdentifier)) {
				workflows.add(workflowIdentifier);
			}
		}
		fireContentsChanged(this, 0, getSize());
		setSelectedItem(firstElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.DefaultComboBoxModel#getSize()
	 */
	@Override
	public int getSize() {
		return workflows.size() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.DefaultComboBoxModel#getElementAt(int)
	 */
	@Override
	public Object getElementAt(int index) {
		return index == 0 ? firstElement : workflows.get(index - 1);
	}

}
