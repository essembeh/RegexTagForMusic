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
package org.essembeh.rtfm.core.actions;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.utils.TaskUtils;
import org.essembeh.rtfm.tasks.ITask;

public class Task {

	private final String identifier;
	private final ITask task;

	public Task(String identifier, String classname) throws TaskException {
		this.identifier = identifier;
		this.task = TaskUtils.instantiateTask(classname);
	}

	/**
	 * 
	 * @param name
	 * @param value
	 */
	public void setProperty(String name, String value) {
		task.setProperty(name, value);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void execute(IMusicFile musicFile) throws ActionException {
		task.execute(musicFile);
	}

	@Override
	public String toString() {
		return identifier;
	}
}
