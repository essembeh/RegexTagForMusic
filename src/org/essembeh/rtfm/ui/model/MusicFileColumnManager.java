package org.essembeh.rtfm.ui.model;

import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;

public class MusicFileColumnManager {

	private final String[] columns = new String[] { "Type", "Path", "Tagged" };

	public int getColumnCount() {
		// return columns.length;
		return 2;
	}

	public String getColumnTitle(int i) {
		return columns[i];
	}

	public Object getValue(IMusicFile musicFile, int i) {
		Object out = "";
		switch (i) {
		case 0:
			out = musicFile.getType().getIdentifier();
			break;
		case 1:
			out = musicFile;
			break;
		case 2:
			Attribute attribute = musicFile.getAttributeList().get("rtfm:tagged");
			out = attribute == null ? null : Boolean.parseBoolean(attribute.getValue());
			break;
		default:
			break;
		}
		return out;
	}

	public boolean isColumnEditable(int i) {
		return false;
	}

	public void setValueAt(IMusicFile musicFile, Object aValue, int columnIndex) {
	}
}
