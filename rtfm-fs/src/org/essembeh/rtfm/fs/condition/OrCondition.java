package org.essembeh.rtfm.fs.condition;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class OrCondition extends MultipleCondition {

	@Override
	public boolean isTrue(IResource resource) {
		boolean out = conditions.isEmpty();
		for (ICondition condition : conditions) {
			if (condition.isTrue(resource)) {
				return true;
			}
		}
		return out;
	}

}
