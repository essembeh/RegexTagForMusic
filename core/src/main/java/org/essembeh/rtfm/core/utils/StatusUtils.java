package org.essembeh.rtfm.core.utils;

import org.essembeh.rtfm.core.exception.ExecutionException;
import org.essembeh.rtfm.core.workflow.ITask;
import org.essembeh.rtfm.core.workflow.report.Severity;
import org.essembeh.rtfm.core.workflow.report.SimpleStatus;

public class StatusUtils {

	public static SimpleStatus executableEnd(ITask task, int returnCode) {
		String message = "Task " + task.getId() + " return code: " + returnCode;
		return new SimpleStatus(returnCode == 0 ? Severity.OK : Severity.WARNING, message);
	}

	public static SimpleStatus executableException(ITask task, ExecutionException e) {
		String message = "Task " + task.getId() + " error: " + e.getMessage();
		return new SimpleStatus(Severity.ERROR, message);
	}

}
