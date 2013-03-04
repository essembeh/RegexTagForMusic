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
package org.essembeh.rtfm.tasks;

import org.essembeh.rtfm.core.exception.WorkflowException;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.utils.commoninterfaces.IConfigurable;

public interface IExecutable extends IConfigurable {

	/**
	 * Execute the task
	 * 
	 * @param file
	 * @throws WorkflowException
	 */
	void execute(IXFile file) throws WorkflowException;

}