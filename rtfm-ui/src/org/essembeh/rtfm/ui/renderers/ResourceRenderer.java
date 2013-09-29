package org.essembeh.rtfm.ui.renderers;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ResourceRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -1130872608459179407L;

	public ResourceRenderer() {
	}

	@Override
	public Component getTableCellRendererComponent(	JTable table,
													Object value,
													boolean isSelected,
													boolean hasFocus,
													int row,
													int column) {
		String valueToPrint = new String();
		if (value != null && value instanceof IResource) {
			IResource resource = (IResource) value;
			valueToPrint = resource.getVirtualPath().toString();
		}
		return super.getTableCellRendererComponent(table, valueToPrint, isSelected, hasFocus, row, column);
	}
}
