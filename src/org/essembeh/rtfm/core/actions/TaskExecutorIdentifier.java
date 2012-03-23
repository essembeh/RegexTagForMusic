package org.essembeh.rtfm.core.actions;

import org.essembeh.rtfm.core.utils.list.Identifier;

public class TaskExecutorIdentifier implements Identifier<TaskExecutor> {

	@Override
	public String getId(TaskExecutor o) {
		return o.getIdentifier();
	}
}
