package org.essembeh.rtfm.ui.handlers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.essembeh.rtfm.core.controller.LibraryScanner;
import org.essembeh.rtfm.core.utils.SimpleJob;
import org.essembeh.rtfm.core.utils.SimpleJobListener;
import org.essembeh.rtfm.model.rtfm.Configuration;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.ui.SharedImages;
import org.essembeh.rtfm.ui.widgets.status.StatusDialog;

public class RefreshLibraryAction extends Action {

	private final Library library;
	private final Configuration configuration;

	public RefreshLibraryAction(Library library, Configuration configuration) {
		super("Refresh library", SharedImages.getImageDescriptor(SharedImages.STAR));
		this.library = library;
		this.configuration = configuration;
	}

	@Override
	public void run() {
		SimpleJob job = new SimpleJob("Refresh Library", IProgressMonitor.UNKNOWN) {
			@Override
			protected IStatus internalRun(IProgressMonitor monitor) throws Exception {
				return LibraryScanner.refresh(library, configuration, monitor);
			}
		};
		job.setUser(true);
		job.asyncLaunch(new SimpleJobListener((j, s) -> {
			if (!s.isOK()) {
				Display.getDefault().syncExec(() -> new StatusDialog(Display.getDefault().getActiveShell(), "Report", s).open());
			}
		}));
	}
}
