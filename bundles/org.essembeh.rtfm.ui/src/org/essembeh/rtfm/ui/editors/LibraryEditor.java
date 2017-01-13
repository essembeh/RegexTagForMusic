package org.essembeh.rtfm.ui.editors;

import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.essembeh.rtfm.core.configuration.ConfigurationManager;
import org.essembeh.rtfm.core.utils.RtfmModelIO;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.StatusUtils;
import org.essembeh.rtfm.ui.handlers.RefreshLibraryAction;
import org.essembeh.rtfm.ui.handlers.XdgOpenAction;
import org.essembeh.rtfm.ui.utils.AppScheduler;
import org.essembeh.rtfm.ui.widgets.LibraryContentProvider;
import org.essembeh.rtfm.ui.widgets.LibraryLabelProvider;
import org.essembeh.rtfm.ui.widgets.LibrarySorter;
import org.essembeh.rtfm.ui.widgets.NodePatterFilter;
import org.essembeh.rtfm.ui.widgets.NodeViewerFilter;
import org.essembeh.rtfm.ui.widgets.RtfmMenuManager;
import org.essembeh.rtfm.ui.widgets.filter.FilterEditDialog;

public class LibraryEditor extends EditorPart implements IResourceChangeListener {

	private final EContentAdapter eContentAdapter = new EContentAdapter() {
		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			LibraryEditor.this.onModelChanged(notification);
		}
	};

	private boolean dirty = false;
	private Library library;
	private Configuration configuration;
	private NodeViewerFilter nodeViewerFilter;
	private TreeViewer nodeTreeViewer;

	public LibraryEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().applyTo(composite);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);
		createFilterComposite(composite);
		createTreeViewer(composite);
		createTreeMenu();
	}

	private void createTreeMenu() {
		RtfmMenuManager menuManager = new RtfmMenuManager(configuration, nodeTreeViewer);
		menuManager.createWorkflowActions();
		menuManager.add(new RefreshLibraryAction(library, configuration));
		menuManager.add(new XdgOpenAction(nodeTreeViewer));
		Menu menu = menuManager.createContextMenu(nodeTreeViewer.getTree());
		nodeTreeViewer.getTree().setMenu(menu);
	}

	private void createTreeViewer(Composite composite) {
		nodeViewerFilter = new NodeViewerFilter();
		FilteredTree filteredTree = new FilteredTree(composite, SWT.MULTI | SWT.BORDER, new NodePatterFilter(), true);
		nodeTreeViewer = filteredTree.getViewer();
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(filteredTree);
		nodeTreeViewer.setContentProvider(new LibraryContentProvider());
		nodeTreeViewer.setLabelProvider(new LibraryLabelProvider(Node::getName));
		nodeTreeViewer.addFilter(nodeViewerFilter);
		nodeTreeViewer.setSorter(new LibrarySorter());
		nodeTreeViewer.setInput(library);
		nodeTreeViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				Object selection = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (selection instanceof Node) {
					nodeTreeViewer.setExpandedState(selection, !nodeTreeViewer.getExpandedState(selection));
				}
			}
		});
		expandDefault();
		getSite().setSelectionProvider(nodeTreeViewer);
	}

	private void createFilterComposite(Composite parent0) {
		Composite parent = new Composite(parent0, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(parent);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).applyTo(parent);

		Button enableFilterButton = new Button(parent, SWT.CHECK);
		enableFilterButton.setText("Enable filtering");
		enableFilterButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				nodeViewerFilter.setFilterEnable(enableFilterButton.getSelection());
				nodeTreeViewer.refresh();
			}
		});

		Button editFilterButton = new Button(parent, SWT.PUSH);
		editFilterButton.setText("Edit filters");
		editFilterButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editFilters();
			}
		});
	}

	protected void editFilters() {
		FilterEditDialog dialog = new FilterEditDialog(getSite().getShell(), nodeViewerFilter.getNodeFilters());
		if (dialog.open() == Window.OK) {
			nodeViewerFilter.updateFilter(library.getRoot(), dialog.getFilters());
			if (nodeViewerFilter.isEnable()) {
				nodeTreeViewer.refresh();
			}
		}
	}

	protected void expandDefault() {
		if (library.getRoot() != null) {
			if (nodeViewerFilter.getNodeFilters().isEmpty() && library.getRoot() != null) {
				nodeTreeViewer.setExpandedState(library.getRoot(), true);
			} else {
				nodeTreeViewer.expandAll();
			}
		}
	}

	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		if (library != null) {
			library.eAdapters().remove(eContentAdapter);
		}
		super.dispose();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		Assert.isTrue(getEditorInput() instanceof FileEditorInput);
		IFile out = ((FileEditorInput) getEditorInput()).getFile();
		Job saveJob = new Job("Save library") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Save to file " + out.getLocation().toOSString(), IProgressMonitor.UNKNOWN);
				try {
					RtfmModelIO.writeLibrary(library, out.getLocation().toFile());
					AppScheduler.runInUiThread(false, () -> setDirty(false));
				} catch (IOException e) {
					return StatusUtils.newErrorStatus(e);
				}
				return Status.OK_STATUS;
			}
		};
		saveJob.setUser(true);
		saveJob.schedule();
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException {
		setSite(site);
		setInput(editorInput);
		if (!(editorInput instanceof IFileEditorInput)) {
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		}
		try {
			loadModel((FileEditorInput) editorInput);
		} catch (IOException e) {
			throw new PartInitException(StatusUtils.newErrorStatus(e));
		}
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(() -> {
			});
		}
	}

	private final void loadModel(FileEditorInput editorInput) throws IOException {
		IFile source = editorInput.getFile();
		library = RtfmModelIO.loadLibrary(source.getLocation().toFile());
		configuration = ConfigurationManager.INSTANCE.load(library.getConfigurationUri());
		library.eAdapters().add(eContentAdapter);
		firePropertyChange(PROP_INPUT);
	}

	@Override
	public boolean isDirty() {
		return dirty;
	}

	protected void setDirty(boolean dirty) {
		this.dirty = dirty;
		firePropertyChange(PROP_DIRTY);
	}

	@Override
	public void setFocus() {
		nodeTreeViewer.getControl().setFocus();
	}

	protected void onModelChanged(Notification notification) {
		if (!notification.isTouch()) {
			setDirty(true);
			if (notification.getNotifier() != null) {
				AppScheduler.runInUiThread(false, () -> nodeTreeViewer.refresh(notification.getNotifier()));
			}
		}
	}
}
