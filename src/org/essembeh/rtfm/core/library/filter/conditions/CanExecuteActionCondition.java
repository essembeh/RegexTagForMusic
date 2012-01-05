package org.essembeh.rtfm.core.library.filter.conditions;

import org.essembeh.rtfm.core.library.file.MusicFile;

public class CanExecuteActionCondition implements Condition {

	String actionName;

	public CanExecuteActionCondition(String actionName) {
		this.actionName = actionName;
	}

	@Override
	public boolean isTrue(MusicFile musicFile) {
		return musicFile.getAllActions().contains(actionName);
	}

	@Override
	public String toString() {
		return "CanExecuteActionCondition [actionName=" + actionName + "]";
	}

}
