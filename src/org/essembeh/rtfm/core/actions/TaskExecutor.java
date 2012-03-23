package org.essembeh.rtfm.core.actions;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class TaskExecutor {

	private String identifier;

	private IRTFMTask rtfmTask;

	public TaskExecutor(String id, String classname) throws TaskException {
		this.identifier = id;
		rtfmTask = TaskUtils.instantiateTask(classname);
	}

	public void setProperty(String key, String value) {
		rtfmTask.setProperty(key, value);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void execute(IMusicFile musicFile) throws ActionException {
		rtfmTask.execute(musicFile);
	}

	@Override
	public String toString() {
		return "TaskExecutor [identifier=" + identifier + ", rtfmTask=" + rtfmTask.getClass().getCanonicalName() + "]";
	}
}
