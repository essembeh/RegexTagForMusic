package org.essembeh.rtfm.ui.widgets.status;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class StatusContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Object[]) {
			return (Object[]) inputElement;
		}
		if (inputElement instanceof Collection) {
			return ((Collection<?>) inputElement).toArray();
		}
		if (inputElement instanceof IStatus) {
			return ((IStatus) inputElement).getChildren();
		}
		return new Object[0];
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof IStatus && ((IStatus) parentElement).isMultiStatus()) {
			return ((IStatus) parentElement).getChildren();
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof IStatus && ((IStatus) element).isMultiStatus() && ((IStatus) element).getChildren().length > 0;
	}

}
