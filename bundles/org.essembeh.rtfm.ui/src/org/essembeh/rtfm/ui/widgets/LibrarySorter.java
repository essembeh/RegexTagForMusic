package org.essembeh.rtfm.ui.widgets;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.NodeUtils;

public class LibrarySorter extends ViewerSorter {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof Node && e2 instanceof Node) {
			return NodeUtils.compare((Node) e1, (Node) e2);
		}
		return super.compare(viewer, e1, e2);
	}
}
