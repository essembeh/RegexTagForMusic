package org.essembeh.rtfm.ui.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;

public class AttributesModel extends AbstractTableModel {

	private final List<IMusicFile> selection;
	private final String[] columns = new String[] { "Name", "Value" };
	private final Map<String, String> data;
	private final static String MULTIPLE_VALUES = "...";

	public AttributesModel() {
		selection = new CopyOnWriteArrayList<IMusicFile>();
		data = new TreeMap<String, String>();
	}

	public void updateSelection(List<IMusicFile> selection) {
		this.selection.clear();
		this.selection.addAll(selection);
		data.clear();
		for (IMusicFile musicFile : selection) {
			for (Attribute attribute : musicFile.getAttributeList()) {
				if (data.containsKey(attribute.getName())) {
					if (!data.get(attribute.getName()).equals(attribute.getValue())) {
						data.put(attribute.getName(), MULTIPLE_VALUES);
					}
				} else {
					data.put(attribute.getName(), attribute.getValue());
				}
			}
		}
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		String out = "";
		if (columnIndex == 0) {
			out = data.keySet().toArray()[rowIndex].toString();
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

	}

}
