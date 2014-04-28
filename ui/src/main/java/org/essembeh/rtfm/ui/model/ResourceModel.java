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

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.core.Application;
import org.essembeh.rtfm.core.config.RtfmProperties;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.ConditionUtils;
import org.essembeh.rtfm.ui.utils.SelectionTool;

public class ResourceModel extends AbstractModel<TableModelListener> implements TableModel {

	private final static String[] TITLES = { "Type", "Virtual path" };
	private final static Class<?>[] CLASSES = { String.class, IResource.class };

	private final List<IResource> content = new ArrayList<>();
	private final RtfmProperties properties;

	public ResourceModel(Application app, final JTree explorerTree, RtfmProperties properties) {
		super(app);
		this.properties = properties;
		explorerTree.getModel().addTreeModelListener(new TreeModelListener() {

			@Override
			public void treeStructureChanged(TreeModelEvent e) {
				refresh(SelectionTool.getSelectedCondition(explorerTree));
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent e) {
				refresh(SelectionTool.getSelectedCondition(explorerTree));
			}

			@Override
			public void treeNodesInserted(TreeModelEvent e) {
				refresh(SelectionTool.getSelectedCondition(explorerTree));
			}

			@Override
			public void treeNodesChanged(TreeModelEvent e) {
				refresh(SelectionTool.getSelectedCondition(explorerTree));
			}
		});
		explorerTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				refresh(SelectionTool.getSelectedCondition(explorerTree));
			}
		});
	}

	public void refresh(ICondition c) {
		if (app.getProject() != null) {
			List<IResource> newContent = app.getProject().getRootFolder()
					.getFilteredResources(ConditionUtils.and(c, getUiFilter()));
			if (!content.equals(newContent)) {
				content.clear();
				content.addAll(newContent);
				TableModelEvent event = new TableModelEvent(this);
				for (TableModelListener l : listeners) {
					l.tableChanged(event);
				}
			}
		} else {
			content.clear();
			TableModelEvent event = new TableModelEvent(this);
			for (TableModelListener l : listeners) {
				l.tableChanged(event);
			}
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

	public List<IResource> getContent() {
		return content;
	}

	private ICondition getUiFilter() {
		ICondition out = null;
		String uiHiddenAttribute = properties.getUiHiddenAttribute();
		if (!StringUtils.isEmpty(uiHiddenAttribute)) {
			out = ConditionUtils.not(ConditionUtils.attributeValueEquals(uiHiddenAttribute, "true"));
		}
		return out;
	}
}
