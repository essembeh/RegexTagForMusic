package org.essembeh.rtfm.ui.widgets.workflow;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.essembeh.rtfm.core.utils.SimpleJob;
import org.essembeh.rtfm.core.utils.SimpleJobListener;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.Workflow;
import org.essembeh.rtfm.model.utils.NodeUtils;
import org.essembeh.rtfm.model.utils.StatusUtils;
import org.essembeh.rtfm.ui.utils.SelectionUtils;
import org.essembeh.rtfm.ui.widgets.LibraryLabelProvider;
import org.essembeh.rtfm.ui.widgets.LibrarySorter;
import org.essembeh.rtfm.ui.widgets.status.StatusDialog;

public class ExecuteWorkflowDialog extends TitleAreaDialog {

	public static ExecuteWorkflowDialog fromSelection(Shell parentShell, Workflow workflow, ISelection selection) {
		Set<Node> nodes = SelectionUtils.getSelectedNodes(selection).stream().flatMap(NodeUtils::recursiveStream).filter(workflow::accept).collect(Collectors.toSet());
		return new ExecuteWorkflowDialog(parentShell, workflow, nodes);
	}

	private final Workflow workflow;
	private final Set<Node> nodes;
	private CheckboxTableViewer tableViewer;

	public ExecuteWorkflowDialog(Shell parentShell, Workflow workflow, Set<Node> nodes) {
		super(parentShell);
		this.workflow = workflow;
		this.nodes = nodes;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayoutFactory.swtDefaults().applyTo(container);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(container);
		createContent(container);

		setTitle(workflow.getName());
		return area;
	}

	private void createContent(Composite parent) {
		tableViewer = CheckboxTableViewer.newCheckList(parent, SWT.SINGLE);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(tableViewer.getControl());

		tableViewer.setLabelProvider(new LibraryLabelProvider(Node::getVirtualPath));
		tableViewer.setContentProvider(ArrayContentProvider.getInstance());
		tableViewer.setSorter(new LibrarySorter());
		tableViewer.setInput(nodes);
		tableViewer.setCheckedElements(nodes.toArray());
	}

	@Override
	protected void okPressed() {
		List<Node> selectedNodes = Stream.of(tableViewer.getCheckedElements()).filter(Node.class::isInstance).map(Node.class::cast).filter(workflow::accept).collect(Collectors.toList());
		if (selectedNodes.isEmpty()) {
			setErrorMessage("No item selected");
		} else {
			SimpleJob job = new SimpleJob(workflow.getName(), selectedNodes.size()) {
				@Override
				protected IStatus internalRun(IProgressMonitor monitor) throws Exception {
					return workflow.execute(new BasicEList<>(selectedNodes), monitor);
				}
			};
			job.asUserJob(true).asyncLaunch(new SimpleJobListener((j, s) -> {
				if (StatusUtils.hasError(s)) {
					Display.getDefault().asyncExec(() -> StatusDialog.open(workflow.getName(), s));
				}
			}));
			super.okPressed();
		}
	}

	@Override
	protected boolean isResizable() {
		return true;
	}
}
