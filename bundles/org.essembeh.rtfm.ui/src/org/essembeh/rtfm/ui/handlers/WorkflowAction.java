package org.essembeh.rtfm.ui.handlers;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Display;
import org.essembeh.rtfm.model.rtfm.Workflow;
import org.essembeh.rtfm.ui.SharedImages;
import org.essembeh.rtfm.ui.widgets.workflow.ExecuteWorkflowDialog;

public class WorkflowAction extends Action {

	private final Workflow workflow;
	private final ISelectionProvider selectionProvider;

	public WorkflowAction(Workflow workflow, ISelectionProvider selectionProvider) {
		this.workflow = workflow;
		this.selectionProvider = selectionProvider;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO different image
		return SharedImages.getImageDescriptor(SharedImages.WAND);
	}

	@Override
	public String getText() {
		return workflow.getName();
	}

	@Override
	public void run() {
		ExecuteWorkflowDialog dialog = ExecuteWorkflowDialog.fromSelection(Display.getDefault().getActiveShell(), workflow, selectionProvider.getSelection());
		dialog.open();
	}
}