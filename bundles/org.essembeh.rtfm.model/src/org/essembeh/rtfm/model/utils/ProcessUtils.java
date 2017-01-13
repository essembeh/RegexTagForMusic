package org.essembeh.rtfm.model.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class ProcessUtils {

	public static boolean isProcessRunning(Process process) {
		try {
			process.exitValue();
		} catch (IllegalThreadStateException e) {
			return true;
		}
		return false;
	}

	public static IStatus execute(ProcessBuilder processBuilder, IProgressMonitor monitor) {
		MultiStatus out = StatusUtils.newMultistatus("Execute " + StringUtils.join(processBuilder.command(), " "));
		Process process = null;
		try {
			process = processBuilder.start();
			while (ProcessUtils.isProcessRunning(process)) {
				if (monitor.isCanceled()) {
					process.destroy();
				}
				Thread.sleep(100);
			}
			int rc = process.exitValue();
			int severity = rc == 0 ? IStatus.OK : IStatus.ERROR;
			out.add(StatusUtils.newStatus(severity, "Process exited with " + rc));
			StatusUtils.safeAdd(out, streamToStatus("Stdout", severity, process.getInputStream()));
			StatusUtils.safeAdd(out, streamToStatus("Stderr", severity, process.getErrorStream()));
		} catch (IOException | InterruptedException e) {
			out.add(StatusUtils.newErrorStatus(e));
		} finally {
			if (process != null) {
				IOUtils.closeQuietly(process.getOutputStream());
				IOUtils.closeQuietly(process.getErrorStream());
				IOUtils.closeQuietly(process.getInputStream());
				process.destroy();
			}
		}
		return out;
	}

	private static IStatus streamToStatus(String message, int severity, InputStream in) {
		IStatus out = null;
		try {
			List<String> content = IOUtils.readLines(in);
			if (!content.isEmpty()) {
				out = StatusUtils.newStatus(severity, message + "\n" + StringUtils.join(content, "\n"));
			}
		} catch (IOException ignored) {
		}
		return out;
	}

}
