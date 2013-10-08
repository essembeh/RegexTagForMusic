package org.essembeh.rtfm.app.workflow.impl;

import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.Configurator;
import org.essembeh.rtfm.app.workflow.IExecutable;

public class CustomTaskDescription extends Configurator<IExecutable> {

	private final TaskDescription taskDescription;

	public CustomTaskDescription(TaskDescription taskDescription) {
		this.taskDescription = taskDescription;
	}

	public TaskDescription getTaskDescription() {
		return taskDescription;
	}

	public IExecutable createInstance() throws TaskInstanciationException {
		return getConfiguredObject(taskDescription.createInstance());
	}
}
