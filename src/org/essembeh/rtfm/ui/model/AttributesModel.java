package org.essembeh.rtfm.ui.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;

public class AttributesModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2842406342280768095L;
	private static final String[] TITLES = new String[] { "Name", "Value" };
	private static final String MULTIPLE_VALUES = "...";
	private final MusicFilesModel musicFilesModel;
	private final MusicFilesSelection musicFilesSelection;
	private final Map<String, String> data;

	/**
	 * 
	 * @param musicFilesModel
	 * @param musicFilesSelection
	 */
	public AttributesModel(MusicFilesModel musicFilesModel, MusicFilesSelection musicFilesSelection) {
		this.musicFilesModel = musicFilesModel;
		this.musicFilesSelection = musicFilesSelection;
		data = new TreeMap<String, String>();
		setupListeners();
		refresh();
	}

	/**
	 * 
	 */
	private void setupListeners() {
		musicFilesModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				refresh();
			}
		});
		musicFilesSelection.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
			}
		});
	}

	public void refresh() {
		List<IMusicFile> files = musicFilesSelection.getSelectedFiles();
		data.clear();
		for (IMusicFile musicFile : files) {
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
