package org.essembeh.rtfm.ui.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.essembeh.rtfm.ui.utils.ColorUtils;

public class AlternatibeRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -1130872608459179407L;

	public AlternatibeRenderer() {
	}

	@Override
	public Component getTableCellRendererComponent(	JTable table,
													Object value,
													boolean isSelected,
													boolean hasFocus,
													int row,
													int column) {
		final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (!isSelected) {
			c.setBackground(row % 2 == 0 ? ColorUtils.ALTERNATIVE : Color.WHITE);
		}
		return c;
	}
}
