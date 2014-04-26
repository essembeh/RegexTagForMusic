package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.ui.utils.SelectionTool;

public class WorkflowModel extends DefaultComboBoxModel<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7840068219637080176L;
	private final static String FIRST_ELEMENT = "Choose an action";

	private final Application app;
	private final List<IWorkflow> workflows;
	private final ResourceModel resourceModel;
	private final JTable resourceTable;

	public WorkflowModel(Application app, ResourceModel resourceModel, JTable resourceTable) {
		super();
		this.app = app;
		this.resourceModel = resourceModel;
		this.resourceTable = resourceTable;
		this.workflows = new ArrayList<>();
		// Setup listeners
		resourceModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				refresh();
			}
		});
		resourceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				refresh();
			}
		});
	}

	protected void refresh() {
		workflows.clear();
		workflows.addAll(app.getWorkflowManager().getCompatibleWorkflows(getSelectedResources()));
		Collections.sort(workflows);
		fireContentsChanged(this, 0, getSize());
		setSelectedItem(FIRST_ELEMENT);
	}

	@Override
	public int getSize() {
		return workflows.size() + 1;
	}

	@Override
	public Object getElementAt(int index) {
		return index == 0 ? FIRST_ELEMENT : workflows.get(index - 1);
	}

	public List<IResource> getSelectedResources() {
		List<IResource> files = SelectionTool.getSelectedResources(resourceTable);
		if (files.size() == 0) {
			files = resourceModel.getContent();
		}
		return files;
	}

}
