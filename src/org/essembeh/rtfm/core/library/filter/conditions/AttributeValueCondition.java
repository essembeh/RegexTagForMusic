package org.essembeh.rtfm.core.library.filter.conditions;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.library.file.MusicFile;

public class AttributeValueCondition implements Condition {

	String attributeName;

	Pattern regexOnValue;

	public AttributeValueCondition(String attributeName, Pattern regexOnValue) {
		this.attributeName = attributeName;
		this.regexOnValue = regexOnValue;
	}

	@Override
	public boolean isTrue(MusicFile musicFile) {
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
		return "AttributeValueCondition [attributeName=" + attributeName + ", regexOnValue=" + regexOnValue + "]";
	}

}
