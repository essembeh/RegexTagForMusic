package org.essembeh.rtfm.cli.utils;

public class WorkflowExecutionException extends Exception {

	private static final long serialVersionUID = 2106258587664695784L;

	public static void check(String commandId, int rc) throws WorkflowExecutionException {
		if (rc != 0) {
			throw new WorkflowExecutionException("Command " + commandId + " exited with " + rc);
		}
	}
	public WorkflowExecutionException() {
		super();
	}

	public WorkflowExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WorkflowExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkflowExecutionException(String message) {
		super(message);
	}

	public WorkflowExecutionException(Throwable cause) {
		super(cause);
	}
}
