package org.essembeh.rtfm.ui.utils;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.essembeh.rtfm.model.rtfm.Node;

public class SelectionUtils {

	public static Set<Node> getSelectedNodes(ISelection selection) {
		Set<Node> out = new TreeSet<>();
		if (selection instanceof ITreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			for (Iterator<?> it = treeSelection.iterator(); it.hasNext();) {
				Object o = it.next();
				if (o instanceof Node) {
					out.add((Node) o);
				}
			}
		} else if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			if (structuredSelection.getFirstElement() instanceof Node) {
				out.add((Node) structuredSelection.getFirstElement());
			}
		}
		return out;

	}
}
