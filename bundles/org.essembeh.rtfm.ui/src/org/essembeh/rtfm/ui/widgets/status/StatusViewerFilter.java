package org.essembeh.rtfm.ui.widgets.status;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class StatusViewerFilter extends ViewerFilter {

	private int severityMinimum = IStatus.OK;

	public StatusViewerFilter(int severity) {
		severityMinimum = Math.min(severity, IStatus.WARNING);
	}

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (element instanceof IStatus) {
			return ((IStatus) element).getSeverity() >= severityMinimum;
		}
		return false;
	}

	public void setSeverityMinimum(int severity) {
		severityMinimum = severity;
	}

	public int getSeverityMinimum() {
		return severityMinimum;
	}

}
