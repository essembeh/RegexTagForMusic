package org.essembeh.rtfm.ui.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.filter.Filter;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.listener.DefaultLibraryListener;

public class MusicFileModel extends AbstractTableModel {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 8190214732252626027L;
	private final Library library;
	private final List<Filter> filters;
	private final List<IMusicFile> currentList;
	private final MusicFileColumnManager columnManager;

	/**
	 * 
	 * @param library
	 */
	public MusicFileModel(final Library library) {
		super();
		this.library = library;
		this.currentList = new ArrayList<IMusicFile>();
		this.columnManager = new MusicFileColumnManager();
		this.filters = new ArrayList<Filter>();

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
		refresh();
	}

	public void refresh() {
		currentList.clear();
		List<IMusicFile> tmpList = library.getAllFiles();
		for (Filter filter : filters) {
			tmpList = filter.filter(tmpList);
		}
		currentList.addAll(tmpList);
		Collections.sort(currentList);
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return columnManager.getColumnCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columnManager.getColumnTitle(columnIndex);
	}

	@Override
	public int getRowCount() {
		return currentList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return columnManager.getValue(currentList.get(rowIndex), columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnManager.isColumnEditable(columnIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		columnManager.setValueAt(currentList.get(rowIndex), aValue, columnIndex);
	}

	public IMusicFile getMusicFileAtRow(int i) {
		return currentList.get(i);
	}

	public void setFilters(List<Filter> filters) {
		this.filters.clear();
		this.filters.addAll(filters);
		refresh();
	}
}
