package org.essembeh.rtfm.fs.condition.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class AttributeExists implements ICondition {

	private final String attributeName;
	private final boolean exists;

	public AttributeExists(String attributeName, boolean exists) {
		this.attributeName = attributeName;
		this.exists = exists;
	}

	@Override
	public boolean isTrue(IResource resource) {
		return !(resource.getAttributes().contains(attributeName) ^ exists);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
