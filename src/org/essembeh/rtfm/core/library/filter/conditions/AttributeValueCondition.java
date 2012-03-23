package org.essembeh.rtfm.core.library.filter.conditions;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;

public class AttributeValueCondition implements IFilterCondition {

	String attributeName;

	Pattern regexOnValue;

	public AttributeValueCondition(String attributeName, Pattern regexOnValue) {
		this.attributeName = attributeName;
		this.regexOnValue = regexOnValue;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		boolean condition = false;
		Attribute attribute = musicFile.getAttributeList().get(attributeName);
		if (attribute != null) {
			String value = attribute.getValue();
			condition = regexOnValue.matcher(value).matches();
		}
		return condition;
	}

	@Override
	public String toString() {
		return "AttributeValueCondition [attributeName=" + attributeName + ", regexOnValue=" + regexOnValue.pattern()
				+ "]";
	}

}
