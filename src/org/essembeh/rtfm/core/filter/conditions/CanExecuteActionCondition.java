/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core.filter.conditions;

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
		return actionService.getWorkflowsForType(musicFile.getType()).contains(actionName);
	}

	@Override
	public String toString() {
		return "CanExecuteActionCondition [actionName=" + actionName + "]";
	}

}
