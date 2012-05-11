package org.essembeh.rtfm.ui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.filter.Filter;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class MusicFileModel extends AbstractTableModel {

	/**
	 * Attributes
	 */
	private static final long serialVersionUID = 8190214732252626027L;
	private final Library library;
	private volatile Filter filter;
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
		addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent arg0) {
				refresh();
			}
		});
		refresh();
	}

	public void refresh() {
		currentList.clear();
		currentList.addAll(library.getAllFiles());
		Collections.sort(currentList);
	}

	/**
	 * 
	 * @return
	 */
	public Filter getFilter() {
		return filter;
	}

	/**
	 * 
	 * @param filter
	 */
	public void setFilter(Filter filter) {
		this.filter = filter;
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

}
