package org.essembeh.rtfm.fs.condition;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class AndCondition extends MultipleCondition {

	@Override
	public boolean isTrue(IResource resource) {
		for (ICondition condition : conditions) {
			if (!condition.isTrue(resource)) {
				return false;
			}
		}
		return true;
	}

}
