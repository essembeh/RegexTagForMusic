package org.essembeh.rtfm.app.utils;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.workflow.impl.TaskDescription;
import org.essembeh.rtfm.app.workflow.report.Severity;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;

public class StatusUtils {

	public static SimpleStatus executableEnd(TaskDescription taskDescription, int returnCode) {
		String message = "Task " + taskDescription.getId() + " return code: " + returnCode;
		return new SimpleStatus(returnCode == 0 ? Severity.OK : Severity.WARNING, message);
	}

	public static SimpleStatus executableException(TaskDescription taskDescription, ExecutionException e) {
		String message = "Task " + taskDescription.getId() + " error: " + e.getMessage();
		return new SimpleStatus(Severity.ERROR, message);
	}

}
