package org.essembeh.rtfm.fs.condition.impl;

import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class AlwaysTrue implements ICondition {

	@Override
	public boolean isTrue(IResource resource) {
		return true;
	}

}
