package org.essembeh.rtfm.ui.renderers;

import java.awt.Component;

import javax.swing.JTable;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ResourceRenderer extends AlternatibeRenderer {

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
		String valueToPrint = value == null ? "" : value.toString();
		if (value != null && value instanceof IResource) {
			IResource resource = (IResource) value;
			valueToPrint = resource.getVirtualPath().toString();
		}
		return super.getTableCellRendererComponent(table, valueToPrint, isSelected, hasFocus, row, column);
	}
}
