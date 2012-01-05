package org.essembeh.rtfm.core.library.filter.conditions;

import org.essembeh.rtfm.core.library.file.MusicFile;

public class NotAttributeCondition implements Condition {

	String attributeName;

	public NotAttributeCondition(String attributeName) {
		this.attributeName = attributeName;
	}

	@Override
	public boolean isTrue(MusicFile musicFile) {
		return !musicFile.getAttributeList().contains(attributeName);
	}

	@Override
	public String toString() {
		return "NotAttributeCondition [attributeName=" + attributeName + "]";
	}

}
