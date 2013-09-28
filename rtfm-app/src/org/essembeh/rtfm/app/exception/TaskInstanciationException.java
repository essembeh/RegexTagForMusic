package org.essembeh.rtfm.app.exception;

import org.essembeh.rtfm.app.workflow.impl.TaskDescription;

public class TaskInstanciationException extends WorkflowException {
	private static final long serialVersionUID = -1290518376508822358L;

	public TaskInstanciationException(TaskDescription taskDescription, Exception e) {
		super(String.format("Cannot create task '%s',  error: %s", taskDescription.getId(), e.getMessage()));
	}

}
