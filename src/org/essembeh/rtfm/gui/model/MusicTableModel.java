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

import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.library.filter.Filter;
import org.essembeh.rtfm.gui.utils.Translator;
import org.essembeh.rtfm.gui.utils.Translator.StringId;

public class MusicTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -8399115137438604846L;

	String[] titles = new String[] { Translator.get(StringId.columnType), Translator.get(StringId.columnPath),
			Translator.get(StringId.columnTagged) };

	Library library = null;

	List<IMusicFile> currentList = null;

	public MusicTableModel(Library lib) {
		this.library = lib;
		// No filter
		updateWithFilter(new Filter());
	}

	public void updateWithFilter(Filter currentFilter) {
		currentList = this.library.getFilteredFiles(currentFilter).toList();
		Collections.sort(currentList);
		fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		Class<?> clazz = String.class;
		switch (arg0) {
		case 0:
			clazz = String.class;
			break;
		case 1:
			clazz = IMusicFile.class;
			break;
		case 2:
			clazz = Boolean.class;
			break;
		default:
			break;
		}
		return clazz;
	}

	@Override
	public int getColumnCount() {
		return this.titles.length;
	}

	@Override
	public String getColumnName(int arg0) {
		return this.titles[arg0];
	}

	@Override
	public int getRowCount() {
		return this.currentList.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Object object = "";
		if (arg0 < this.currentList.size()) {
			IMusicFile mf = this.currentList.get(arg0);
			switch (arg1) {
			case 0:
				object = mf.getType();
				break;
			case 1:
				object = mf;
				break;
			case 2:
				Attribute att = mf.getAttributeList().get("tagged");
				if (att == null) {
					object = null;
				} else {
					object = new Boolean(att.getValue());
				}
				break;
			default:
				break;
			}
		}
		return object;
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public List<FileType> getTypeList() {
		return FileType.getAllFileTypes();
	}
}
