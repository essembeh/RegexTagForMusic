package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.condition.MultipleCondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.ConditionUtils;
import org.essembeh.rtfm.ui.model.ExplorerNodeUtils.NamedFilter;

public class ResourceModel extends AbstractModel<TableModelListener> implements TableModel {

	private final static String[] TITLES = { "Type", "Virtual path" };
	private final static Class<?>[] CLASSES = { String.class, IResource.class };

	private final List<IResource> content = new ArrayList<>();

	public ResourceModel(Application app, final JTree explorerTree) {
		super(app);
		explorerTree.getModel().addTreeModelListener(new TreeModelListener() {

			@Override
			public void treeStructureChanged(TreeModelEvent e) {
				refreshWithSelectedFilters(explorerTree);
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent e) {
				refreshWithSelectedFilters(explorerTree);
			}

			@Override
			public void treeNodesInserted(TreeModelEvent e) {
				refreshWithSelectedFilters(explorerTree);
			}

			@Override
			public void treeNodesChanged(TreeModelEvent e) {
				refreshWithSelectedFilters(explorerTree);
			}
		});
		explorerTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				refreshWithSelectedFilters(explorerTree);
			}
		});
	}

	protected void refreshWithSelectedFilters(JTree explorerTree) {
		MultipleCondition condition = ConditionUtils.andCondition();
		TreePath[] selectedPaths = explorerTree.getSelectionPaths();
		if (selectedPaths != null) {
			for (TreePath treePath : selectedPaths) {
				Object lastPathElement = treePath.getLastPathComponent();
				if (lastPathElement instanceof DefaultMutableTreeNode) {
					Object userObject = ((DefaultMutableTreeNode) lastPathElement).getUserObject();
					if (userObject instanceof NamedFilter) {
						condition.addCondition(((NamedFilter) userObject).getCondition());
					}
				}
			}
		}
		refresh(condition);
	}

	public void refresh(ICondition c) {
		content.clear();
		if (app.getProject() != null) {
			content.addAll(app.getProject().getRootFolder().getFilteredResources(c));
		}
		TableModelEvent event = new TableModelEvent(this);
		for (TableModelListener l : listeners) {
			l.tableChanged(event);
		}
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		addListener(l);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return CLASSES[columnIndex];
	}

	@Override
	public int getColumnCount() {
		return TITLES.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return TITLES[columnIndex];
	}

	@Override
	public int getRowCount() {
		return content.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		IResource r = content.get(rowIndex);
		if (columnIndex == 0) {
			return r.getAttributes().getFilehandler();
		}
		return r;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		removeListener(l);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

}
