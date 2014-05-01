package org.essembeh.rtfm.fs.condition.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.AttributesHelper;

public class AttributeValueEquals implements ICondition {

	private final String attributeName;
	private final String expectedValue;

	public AttributeValueEquals(String attributeName, String expectedValue) {
		this.attributeName = attributeName;
		this.expectedValue = expectedValue;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean isTrue(IResource resource) {
		return resource.getAttributes().containsKey(attributeName)
				&& StringUtils.equals(expectedValue, AttributesHelper.get(resource, attributeName));
	}

}
