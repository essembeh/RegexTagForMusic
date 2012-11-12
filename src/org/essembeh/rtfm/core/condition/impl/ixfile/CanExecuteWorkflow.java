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
package org.essembeh.rtfm.core.condition.impl.ixfile;

import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.configuration.IWorkflowService;
import org.essembeh.rtfm.core.library.file.IXFile;

public class CanExecuteWorkflow implements ICondition<IXFile> {

	/**
	 * 
	 */
	private final String actionName;
	private final IWorkflowService workflowService;

	/**
	 * 
	 * @param workflowService
	 * @param actionName
	 */
	public CanExecuteWorkflow(IWorkflowService workflowService, String actionName) {
		this.actionName = actionName;
		this.workflowService = workflowService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(IXFile input) {
		return workflowService.getWorkflowsForType(input.getType()).contains(actionName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + " [actionName=" + actionName + "]";
	}

}
