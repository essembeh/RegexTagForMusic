package org.essembeh.rtfm.ui.model;

import java.io.File;

import javax.swing.tree.DefaultTreeModel;

import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.listener.DefaultLibraryListener;

public class FiltersModel extends DefaultTreeModel {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 5953683730500669601L;
	private final ExplorerNodeUtils nodeUtils;

	/**
	 * Constructor
	 * 
	 * @param nodeUtils
	 * @param asksAllowsChildren
	 */
	public FiltersModel(Library library, ExplorerNodeUtils nodeUtils, boolean asksAllowsChildren) {
		super(nodeUtils.buildRoot(), asksAllowsChildren);
		this.nodeUtils = nodeUtils;
		library.addListener(new DefaultLibraryListener() {
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

	/**
	 * Refresh from library
	 */
	private void refresh() {
		nodeStructureChanged(nodeUtils.buildRoot());
	}
}
