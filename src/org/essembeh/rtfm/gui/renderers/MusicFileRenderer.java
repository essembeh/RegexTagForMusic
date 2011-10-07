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
package org.essembeh.rtfm.gui.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.essembeh.rtfm.core.MusicFile;

public class MusicFileRenderer extends DefaultTableCellRenderer {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -1130872608459179407L;

	/**
	 * Constructor
	 */
	public MusicFileRenderer() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax
	 * .swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(	JTable table,
													Object value,
													boolean isSelected,
													boolean hasFocus,
													int row,
													int column) {
		String valueToPrint = new String();
		if (value != null && value instanceof MusicFile) {
			MusicFile mf = (MusicFile) value;
			valueToPrint = mf.getVirtualPath();
		}
		return super.getTableCellRendererComponent(table, valueToPrint, isSelected, hasFocus, row, column);
	}
}