package org.essembeh.rtfm.core.actions;

import org.essembeh.rtfm.core.utils.list.Identifier;

public class ActionIdentifier implements Identifier<Action> {

	@Override
	public String getId(Action o) {
		return o.getIdentifier();
	}
}
