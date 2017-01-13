package org.essembeh.rtfm.ui.widgets;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.NodeUtils;

public class LibraryContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object in) {
		if (in instanceof List) {
			return ((List<?>) in).toArray();
		}
		if (in instanceof Node) {
			return ((Node) in).getContent().values().toArray();
		}
		if (in instanceof Library) {
			Library lib = (Library) in;
			if (lib.getRoot() == null) {
				return new Object[0];
			}
			return new Object[] { lib.getRoot() };
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object in) {
		return getElements(in);
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Node) {
			Object out = ((Node) element).getParentNode();
			if (out == null) {
				out = NodeUtils.getLibrary((Node) element);
			}
			return out;
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Library) {
			return hasChildren(((Library) element).getRoot());
		}
		if (element instanceof Node) {
			return ((Node) element).isFolder() && !((Node) element).getContent().isEmpty();
		}
		return false;
	}

}
