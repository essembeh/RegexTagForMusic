package org.essembeh.rtfm.fs.condition.impl;

import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class AttributeValueMatches implements ICondition {

	private final String attributeName;
	private final Pattern regexOnValue;

	public AttributeValueMatches(String attributeName, Pattern regexOnValue) {
		this.attributeName = attributeName;
		this.regexOnValue = regexOnValue;
	}

	@Override
	public boolean isTrue(IResource resource) {
		if (resource.getAttributes().contains(attributeName)) {
			return regexOnValue.matcher(resource.getAttributes().getValue(attributeName)).matches();
		}
		return false;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
