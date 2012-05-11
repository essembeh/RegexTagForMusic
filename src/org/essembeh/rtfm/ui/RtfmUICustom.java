package org.essembeh.rtfm.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.essembeh.rtfm.core.filter.Filter;
import org.essembeh.rtfm.ui.controller.MainController;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils.NamedFilter;

public class RtfmUICustom extends RtfmUI {

	public RtfmUICustom(MainController controller) {
		super(controller);

		// Listeners
		fileTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int[] selectedRows = fileTable.getSelectedRows();
				mainController.updateSelection(selectedRows);
			}
		});
		explorerTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				List<Filter> filters = new ArrayList<Filter>();
				TreePath[] selectedPaths = explorerTree.getSelectionPaths();
				for (TreePath treePath : selectedPaths) {
					Object lastPathElement = treePath.getLastPathComponent();
					if (lastPathElement instanceof DefaultMutableTreeNode) {
						Object userObject = ((DefaultMutableTreeNode) lastPathElement).getUserObject();
						if (userObject instanceof NamedFilter) {
							Filter filter = ((NamedFilter) userObject).getFilter();
							if (filter != null) {
								filters.add(filter);
							}
						}
					}
				}
				mainController.updateSelectedFilter(filters);
			}
		});
	}

}
