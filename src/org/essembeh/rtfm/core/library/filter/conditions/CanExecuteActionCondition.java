package org.essembeh.rtfm.core.library.filter.conditions;

import org.essembeh.rtfm.core.configuration.ActionService;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class CanExecuteActionCondition implements IFilterCondition {

	private String actionName;
	private ActionService actionService;

	public CanExecuteActionCondition(ActionService actionService, String actionName) {
		this.actionName = actionName;
		this.actionService = actionService;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		return actionService.getWorkflowIdentifiersForType(musicFile.getType()).contains(actionName);
	}

	@Override
	public String toString() {
		return "CanExecuteActionCondition [actionName=" + actionName + "]";
	}

}
