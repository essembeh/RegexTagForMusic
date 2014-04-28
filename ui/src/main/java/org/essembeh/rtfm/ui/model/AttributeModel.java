package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.ui.utils.SelectionTool;

public class AttributeModel extends AbstractTableModel {

	/**
	 * Attributes
	 */
	private static final Logger logger = Logger.getLogger(AttributeModel.class);
	private static final long serialVersionUID = -2842406342280768095L;
	private static final String[] TITLES = new String[] { "Name", "Value" };
	private static final String MULTIPLE_VALUES = "...";
	private final ResourceModel resourceModel;
	private final JTable resourceTable;
	private final TreeMap<String, String> data;
	private final List<IResource> selectedResources;
	private final ConditionModel filterModel;

	/**
	 * 
	 * @param filterModel
	 * @param musicFilesModel
	 * @param musicFilesSelection
	 */
	public AttributeModel(ResourceModel resourceModel, JTable resourceTable, ConditionModel filterModel) {
		this.filterModel = filterModel;
		this.resourceModel = resourceModel;
		this.resourceTable = resourceTable;
		selectedResources = new ArrayList<>();
		data = new TreeMap<>();
		setupListeners();
		refresh();
	}

	/**
	 * 
	 */
	private void setupListeners() {
		resourceModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				refresh();
			}
		});
		resourceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				refresh();
			}
		});
	}

	public void refresh() {
		data.clear();
		selectedResources.clear();
		for (IResource r : SelectionTool.getSelectedResources(resourceTable)) {
			selectedResources.add(r);
			for (Entry<String, String> attribute : r.getAttributes().entrySet()) {
				if (data.containsKey(attribute.getKey())) {
					if (!data.get(attribute.getKey()).equals(attribute.getValue())) {
						data.put(attribute.getKey(), MULTIPLE_VALUES);
					}
				} else {
					data.put(attribute.getKey(), attribute.getValue());
				}
			}
		}
		fireTableDataChanged();
	}

	private String getAttributeName(int index) {
		return data.keySet().toArray(new String[] {})[index];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
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
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String out = "";
		if (columnIndex == 0) {
			out = getAttributeName(rowIndex);
		} else if (columnIndex == 1) {
			out = data.get(getValueAt(rowIndex, 0));
		}
		return out;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (MULTIPLE_VALUES.equals(aValue)) {
			logger.debug("Do not update with value: " + MULTIPLE_VALUES);
		} else {
			String attributeName = getAttributeName(rowIndex);
			logger.info("Update attribute: " + attributeName + ", with value: " + aValue);
			for (IResource r : selectedResources) {
				logger.debug("Update file: " + r);
				r.getAttributes().updateValue(attributeName, aValue.toString());
			}
			refresh();
			filterModel.refresh();
		}
	}
}
