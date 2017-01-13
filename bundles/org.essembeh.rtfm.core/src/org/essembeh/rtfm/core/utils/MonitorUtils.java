package org.essembeh.rtfm.core.utils;

import org.eclipse.core.runtime.IProgressMonitor;

public class MonitorUtils {

	public static IProgressMonitor check(IProgressMonitor monitor) throws InterruptedException {
		if (monitor.isCanceled()) {
			throw new InterruptedException("Operation canceled");
		}
		return monitor;
	}

	public static IProgressMonitor update(IProgressMonitor monitor, String taskName, String subTask) throws InterruptedException {
		check(monitor);
		if (taskName != null) {
			monitor.setTaskName(taskName);
		}
		if (subTask != null) {
			monitor.subTask(subTask);
		}
		return monitor;
	}
}
