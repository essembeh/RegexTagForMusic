package org.essembeh.rtfm.ui.model;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.file.IXFile;

public class AttributesModel extends AbstractTableModel {

	/**
	 * Attributes
	 */
	private static final Logger logger = Logger.getLogger(AttributesModel.class);
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

	/**
	 * 
	 */
	public void refresh() {
		List<IXFile> files = musicFilesSelection.getSelectedFiles();
		data.clear();
		for (IXFile musicFile : files) {
			for (Entry<String, String> attribute : musicFile.getAttributes().entrySet()) {
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

	/**
	 * 
	 * @param index
	 * @return
	 */
	private String getAttributeName(int index) {
		return data.keySet().toArray(new String[] {})[index];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return TITLES.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return TITLES[columnIndex];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return data.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex == 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (MULTIPLE_VALUES.equals(aValue)) {
			logger.debug("Do not update with value: " + MULTIPLE_VALUES);
		} else {
			String attributeName = getAttributeName(rowIndex);
			logger.info("Update attribute: " + attributeName + ", with value: " + aValue);
			List<IXFile> list = musicFilesSelection.getSelectedFiles();
			for (IXFile musicFile : list) {
				logger.debug("Update file: " + musicFile);
				musicFile.getAttributes().updateIfExists(attributeName, aValue.toString());
			}
			refresh();
		}
	}
}
