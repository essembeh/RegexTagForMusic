package org.essembeh.rtfm.core.utils.identifiers;

import org.essembeh.rtfm.core.actions.Task;
import org.essembeh.rtfm.core.utils.list.Identifier;

public class TaskIdentifier implements Identifier<Task> {

	@Override
	public String getId(Task o) {
		return o.getIdentifier();
	}
}
