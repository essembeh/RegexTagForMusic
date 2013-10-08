package org.essembeh.rtfm.app.utils;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.workflow.ITask;
import org.essembeh.rtfm.app.workflow.report.Severity;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;

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
