package org.essembeh.rtfm.core.workflow.impl;

import org.essembeh.rtfm.core.exception.TaskInstanciationException;
import org.essembeh.rtfm.core.utils.Configurator;
import org.essembeh.rtfm.core.workflow.IExecutable;

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
