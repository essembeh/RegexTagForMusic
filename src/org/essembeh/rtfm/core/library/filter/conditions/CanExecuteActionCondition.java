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
package org.essembeh.rtfm.core.library.filter.conditions;

import org.essembeh.rtfm.core.configuration.IExecutionEnvironment;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class CanExecuteActionCondition implements IFilterCondition {

	private final String actionName;
	private final IExecutionEnvironment coreConfiguration;

	public CanExecuteActionCondition(IExecutionEnvironment coreConfiguration, String actionName) {
		this.actionName = actionName;
		this.coreConfiguration = coreConfiguration;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		return coreConfiguration.getWorkflowsForType(musicFile.getType()).contains(actionName);
	}

	@Override
	public String toString() {
		return "CanExecuteActionCondition [actionName=" + actionName + "]";
	}

}
