package org.essembeh.rtfm.ui.model;

import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.properties.SpecialAttribute;

public class MusicFileColumnManager {

	private final String[] columns = new String[] { "Type", "Path", "Tagged" };
	private final RTFMProperties properties;

	public MusicFileColumnManager(RTFMProperties properties) {
		this.properties = properties;
	}

	public int getColumnCount() {
		// return columns.length;
		return 2;
	}

	public String getColumnTitle(int i) {
		return columns[i];
	}

	public Object getValue(IXFile musicFile, int i) {
		Object out = "";
		switch (i) {
		case 0:
			out = musicFile.getType().getIdentifier();
			break;
		case 1:
			out = musicFile;
			break;
		case 2:
			String attributeValue = musicFile.getAttributes().getAttributeValue(properties.getSpecialAttribute(SpecialAttribute.MUSIC_TAGGED));
			out = attributeValue == null ? null : Boolean.parseBoolean(attributeValue);
			break;
		default:
			break;
		}
		return out;
	}

	public boolean isColumnEditable(int i) {
		return false;
	}

	public void setValueAt(IXFile musicFile, Object aValue, int columnIndex) {
	}
}
