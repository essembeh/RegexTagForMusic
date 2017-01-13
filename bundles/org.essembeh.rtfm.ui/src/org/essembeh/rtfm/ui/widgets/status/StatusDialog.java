package org.essembeh.rtfm.ui.widgets.status;

import java.util.Map;
import java.util.TreeMap;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;

public class StatusDialog extends TitleAreaDialog {

	private final IStatus status;
	private StatusViewerFilter filter;
	private TreeViewer treeViewer;
	private static final Map<Integer, String> LEVELS = new TreeMap<>();

	static {
		LEVELS.put(IStatus.OK, "All");
		LEVELS.put(IStatus.INFO, "Info");
		LEVELS.put(IStatus.WARNING, "Warning");
		LEVELS.put(IStatus.ERROR, "Error");
		LEVELS.put(IStatus.CANCEL, "Cancel");
	}

	public StatusDialog(Shell parentShell, String title, IStatus input) {
		super(parentShell);
		this.status = input;
		setTitle(title);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridLayoutFactory.swtDefaults().applyTo(container);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(container);
		createContent(container);

		int level = IMessageProvider.NONE;
		if (status.getSeverity() == IStatus.INFO) {
			level = IMessageProvider.INFORMATION;
		} else if (status.getSeverity() == IStatus.WARNING) {
			level = IMessageProvider.WARNING;
		} else if (status.getSeverity() == IStatus.ERROR) {
			level = IMessageProvider.ERROR;
		} else if (status.getSeverity() == IStatus.CANCEL) {
			level = IMessageProvider.ERROR;
		}
		setMessage(status.getMessage(), level);

		refreshWithLevel();
		return area;
	}

	private void createContent(Composite container) {
		filter = new StatusViewerFilter(status.getSeverity());

		ComboViewer levelComboViewer = new ComboViewer(container, SWT.READ_ONLY);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).applyTo(levelComboViewer.getControl());
		levelComboViewer.setContentProvider(ArrayContentProvider.getInstance());
		levelComboViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				return LEVELS.get(element);
			}
		});
		levelComboViewer.setInput(LEVELS.keySet().stream().filter(n -> n <= status.getSeverity()).toArray());
		levelComboViewer.addFilter(new ViewerFilter() {
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof Integer) {
					return ((int) element) <= status.getSeverity();
				}
				return false;
			}
		});
		levelComboViewer.setSelection(new StructuredSelection(filter.getSeverityMinimum()));

		FilteredTree filteredTree = new FilteredTree(container, SWT.READ_ONLY, new PatternFilter(), true);
		treeViewer = filteredTree.getViewer();
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(filteredTree);

		treeViewer.setContentProvider(new StatusContentProvider());
		treeViewer.setLabelProvider(new StatusLabelProvider());
		treeViewer.setInput(status);
		treeViewer.addFilter(filter);

		levelComboViewer.addSelectionChangedListener(e -> {
			IStructuredSelection sel = (IStructuredSelection) e.getSelection();
			if (sel.getFirstElement() instanceof Integer) {
				filter.setSeverityMinimum((int) sel.getFirstElement());
				refreshWithLevel();
			}
		});
	}

	protected void refreshWithLevel() {
		treeViewer.refresh();
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	public static void open(String title, IStatus input) {
		StatusDialog dialog = new StatusDialog(Display.getDefault().getActiveShell(), title, input);
		dialog.open();
	}
}
