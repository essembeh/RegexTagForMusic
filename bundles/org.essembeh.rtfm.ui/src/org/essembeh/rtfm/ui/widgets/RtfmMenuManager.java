package org.essembeh.rtfm.ui.widgets;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.Workflow;
import org.essembeh.rtfm.ui.handlers.WorkflowAction;

public class RtfmMenuManager extends MenuManager {

	private final Configuration configuration;
	private final ISelectionProvider selectionProvider;

	public RtfmMenuManager(Configuration configuration, ISelectionProvider selectionProvider) {
		this.configuration = configuration;
		this.selectionProvider = selectionProvider;
	}

	public void createWorkflowActions() {
		for (Workflow workflow : configuration.getWorkflows()) {
			WorkflowAction action = new WorkflowAction(workflow, selectionProvider);
			add(action);
		}
	}
}
