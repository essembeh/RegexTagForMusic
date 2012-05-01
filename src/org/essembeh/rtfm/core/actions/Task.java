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
