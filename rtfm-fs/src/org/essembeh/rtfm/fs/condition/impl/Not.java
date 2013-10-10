package org.essembeh.rtfm.fs.condition.impl;

import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class Not implements ICondition {

	private final ICondition condition;

	public Not(ICondition condition) {
		this.condition = condition;
	}

	@Override
	public boolean isTrue(IResource resource) {
		return !condition.isTrue(resource);
	}

}
