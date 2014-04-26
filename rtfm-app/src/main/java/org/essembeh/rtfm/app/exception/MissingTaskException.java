package org.essembeh.rtfm.app.exception;

public class MissingTaskException extends WorkflowException {
	private static final long serialVersionUID = 820369463198260608L;

	public MissingTaskException(String workflow, String taskRef) {
		super(String.format("Cannot find task: %s in workflow: %s", taskRef, workflow));
	}

}
