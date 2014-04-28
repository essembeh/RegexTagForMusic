package org.essembeh.rtfm.ui.model;

import javax.swing.tree.DefaultTreeModel;

public class ConditionModel extends DefaultTreeModel {

	private static final long serialVersionUID = 6427894098965365083L;
	private final ExplorerNodeUtils nodeUtils;

	public ConditionModel(ExplorerNodeUtils nodeUtils, boolean asksAllowsChildren) {
		super(nodeUtils.buildRoot(), asksAllowsChildren);
		this.nodeUtils = nodeUtils;
	}

	public void refresh() {
		nodeStructureChanged(nodeUtils.buildRoot());
	}
}
