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
