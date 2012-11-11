package org.essembeh.rtfm.ui.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.filter.Filter;
import org.essembeh.rtfm.core.library.listener.DefaultLibraryListener;

public class MusicFilesModel extends AbstractTableModel {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 8190214732252626027L;
	private final Library library;
	private final FiltersSelection filtersSelection;
	private final List<IMusicFile> currentList;
	private final MusicFileColumnManager columnManager;

	/**
	 * 
	 * @param library
	 * @param explorerModel
	 */
	public MusicFilesModel(final Library library, FiltersSelection filtersSelection) {
		super();
		this.library = library;
		this.filtersSelection = filtersSelection;
		this.currentList = new ArrayList<IMusicFile>();
		this.columnManager = new MusicFileColumnManager();

		setupListeners();
		refresh();
	}

	/**
	 * Setup listeners
	 */
	private void setupListeners() {
		library.addListener(new DefaultLibraryListener() {
			@Override
			public void loadLibraryFailed() {
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
		filtersSelection.addListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				refresh();
			}
		});
	}

	/**
	 * Refresh list with selected filters
	 */
	public void refresh() {
		currentList.clear();
		List<IMusicFile> tmpList = library.getAllFiles();
		for (Filter filter : filtersSelection.getCurrentFilters()) {
			tmpList = filter.filter(tmpList);
		}
		currentList.addAll(tmpList);
		Collections.sort(currentList);
		fireTableDataChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnIndex == 0 ? String.class : IMusicFile.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return columnManager.getColumnCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int columnIndex) {
		return columnManager.getColumnTitle(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return currentList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return columnManager.getValue(currentList.get(rowIndex), columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnManager.isColumnEditable(columnIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		columnManager.setValueAt(currentList.get(rowIndex), aValue, columnIndex);
	}

	/**
	 * Compute "pseudo optimal" length of a column
	 * 
	 * @param col
	 * @return
	 */
	public int getOptimalWidth(int col) {
		int width = 100;
		if (col == 0) {
			int maxLen = 0;
			for (IMusicFile musicFile : currentList) {
				maxLen = Math.max(maxLen, columnManager.getValue(musicFile, 0).toString().length());
			}
			width = maxLen * 12;
		}
		return width;
	}

	/**
	 * 
	 * @return
	 */
	public List<IMusicFile> getFilteredFiles() {
		return currentList;
	}
}
