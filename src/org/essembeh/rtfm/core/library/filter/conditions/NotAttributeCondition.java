package org.essembeh.rtfm.core.library.filter.conditions;

import org.essembeh.rtfm.core.library.file.IMusicFile;

public class NotAttributeCondition implements IFilterCondition {

	String attributeName;

	public NotAttributeCondition(String attributeName) {
		this.attributeName = attributeName;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		return !musicFile.getAttributeList().containsKey(attributeName);
	}

	@Override
	public String toString() {
		return "NotAttributeCondition [attributeName=" + attributeName + "]";
	}

}
