/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.gui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class MusicManagerModel extends AbstractTableModel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -8399115137438604846L;

	/**
	 * Columns titles
	 */
	String[] titles = new String[] { "Type", "Path", "Tagged" };

	/**
	 * The Application containing data
	 */
	MusicManager app = null;

	/**
	 * The current list of filtered files by the Application
	 */
	List<IMusicFile> list = null;

	/**
	 * Constuctor
	 * 
	 * @param app
	 */
	public MusicManagerModel(MusicManager app) {
		this.app = app;
		// No filter
		updateWithFilter(Filter.ALL);
	}

	/**
	 * Update the list of files with given filter.
	 * 
	 * @param currentFilter
	 */
	public void updateWithFilter(Filter currentFilter) {
		this.list = this.app.getFilteredFiles(currentFilter);
		fireTableStructureChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int arg0) {
		Class<?> clazz = String.class;
		if (arg0 == 1) {
			clazz = IMusicFile.class;
		}
		return clazz;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return this.titles.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int arg0) {
		return this.titles[arg0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return this.list.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int arg0, int arg1) {
		Object object = "";
		if (arg0 < this.list.size()) {
			IMusicFile mf = this.list.get(arg0);
			if (arg1 == 0) {
				object = mf.getType();
			} else if (arg1 == 1) {
				object = mf;
			} else {
				if (mf.isTaggable()) {
					object = "" + mf.isTagged();
				}
			}
		}
		return object;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}
}
