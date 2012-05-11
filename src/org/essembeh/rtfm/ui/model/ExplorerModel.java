package org.essembeh.rtfm.ui.model;

import java.io.File;

import javax.swing.tree.DefaultTreeModel;

import org.essembeh.rtfm.core.library.listener.DefaultLibraryListener;

public class ExplorerModel extends DefaultTreeModel {

	private final ExplorerNodeUtils nodeUtils;

	public ExplorerModel(ExplorerNodeUtils nodeUtils, boolean asksAllowsChildren) {
		super(nodeUtils.getRoot(), asksAllowsChildren);
		this.nodeUtils = nodeUtils;
		nodeUtils.getLibrary().addListener(new DefaultLibraryListener() {
			@Override
			public void loadLibraryFailed(File source) {
				refresh();
			}

			@Override
			public void loadLibrarySucceeeded() {
				refresh();
			}

			@Override
			public void scanFolderFailed(File folder) {
				refresh();
			}

			@Override
			public void scanFolderSucceeeded() {
				refresh();
			}
		});
	}

	public void refresh() {
		nodeUtils.refreshTreeNode();
		nodeStructureChanged(nodeUtils.getRoot());
	}
}
