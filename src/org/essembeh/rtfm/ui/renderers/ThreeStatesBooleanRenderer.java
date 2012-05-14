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
package org.essembeh.rtfm.ui.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ThreeStatesBooleanRenderer implements TableCellRenderer {

	private final JLabel stateNull;
	private final JCheckBox stateTrue;
	private final JCheckBox stateFalse;

	public ThreeStatesBooleanRenderer() {
		this.stateNull = new JLabel();
		this.stateTrue = new JCheckBox();
		this.stateFalse = new JCheckBox();
		this.stateTrue.setSelected(true);
		this.stateTrue.setBackground(Color.WHITE);
		this.stateFalse.setSelected(false);
		this.stateFalse.setBackground(Color.WHITE);
	}

	@Override
	public Component getTableCellRendererComponent(	JTable table,
													Object value,
													boolean isSelected,
													boolean hasFocus,
													int row,
													int column) {
		Component c = this.stateNull;
		if (value != null && value instanceof Boolean) {
			if ((Boolean) value == true) {
				c = this.stateTrue;
			} else {
				c = this.stateFalse;
			}
		}
		return c;
	}
}
