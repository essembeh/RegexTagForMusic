package org.essembeh.rtfm.ui.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.essembeh.rtfm.model.filter.INodeFilter;
import org.essembeh.rtfm.model.rtfm.Node;

public class NodeViewerFilter extends ViewerFilter {

	private boolean enable = false;
	private final List<INodeFilter> filters = new ArrayList<>();
	private Set<Node> visibleNodes = new TreeSet<>();

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if (enable && !filters.isEmpty()) {
			return visibleNodes.contains(element);
		}
		return true;
	}

	public void updateFilter(Node root, List<INodeFilter> filters) {
		this.filters.clear();
		this.filters.addAll(filters);
		visibleNodes.clear();
		if (!filters.isEmpty()) {
			checkNode(root, visibleNodes);
		}
	}

	public List<INodeFilter> getNodeFilters() {
		return filters;
	}

	private boolean checkNode(Node in, Set<Node> selection) {
		boolean out = false;
		for (Node n : in.getContent().values()) {
			if (checkNode(n, selection)) {
				out = true;
			}
		}
		if (!out) {
			out = isVisible(in);
		}
		if (out) {
			selection.add(in);
		}
		return out;
	}

	private boolean isVisible(Node in) {
		for (INodeFilter f : filters) {
			if (!f.test(in)) {
				return false;
			}
		}
		return true;
	}

	public void setFilterEnable(boolean selection) {
		enable = selection;
	}

	public boolean isEnable() {
		return enable;
	}

}
