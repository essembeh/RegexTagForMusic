package org.essembeh.rtfm.ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.essembeh.rtfm.core.filter.Filter;
import org.essembeh.rtfm.gui.renderers.ThreeStatesBooleanRenderer;
import org.essembeh.rtfm.ui.action.AbstractRtfmAction;
import org.essembeh.rtfm.ui.controller.MainController;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils.NamedFilter;
import org.essembeh.rtfm.ui.utils.Image;

public class RtfmUICustom extends RtfmUI {

	public RtfmUICustom(MainController controller) {
		super(controller);

		// Model
		attributeTable.setModel(mainController.getAttributesModel());
		fileTable.setModel(mainController.getFileModel());
		explorerTree.setModel(mainController.getExplorerModel());

		// Size
		fileTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		fileTable.getColumnModel().getColumn(2).setPreferredWidth(70);
		attributeTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		attributeTable.getColumnModel().getColumn(1).setPreferredWidth(200);

		// Renderes
		fileTable.setDefaultRenderer(Boolean.class, new ThreeStatesBooleanRenderer());

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
				if (selectedPaths != null) {
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
				}
				mainController.updateSelectedFilter(filters);
			}
		});

		// Add actions
		actionPanel.add(new JButton(new AbstractRtfmAction("Scan", Image.FILE_NEW) {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainController.scanFolder();
			}
		}));
		actionPanel.add(new JButton(new AbstractRtfmAction("Open", Image.FILE_OPEN) {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainController.loadDatabase();
			}
		}));
		actionPanel.add(new JButton(new AbstractRtfmAction("Save", Image.FILE_SAVE) {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainController.saveDatabase();
			}
		}));

		mainController.createWorkflowActionModel(workflowPanel);
	}
}
