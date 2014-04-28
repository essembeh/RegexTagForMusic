package org.essembeh.rtfm.core.exception;

import org.essembeh.rtfm.fs.exception.CommonException;

public abstract class WorkflowException extends CommonException {
	private static final long serialVersionUID = 1L;

	public WorkflowException(String message) {
		super(message);
	}

}
