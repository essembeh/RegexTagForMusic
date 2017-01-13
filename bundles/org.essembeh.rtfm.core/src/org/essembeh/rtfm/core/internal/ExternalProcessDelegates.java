package org.essembeh.rtfm.core.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.essembeh.rtfm.core.utils.StatusUtils;
import org.essembeh.rtfm.model.utils.ProcessUtils;

public class ExternalProcessDelegates {

	public static IStatus execute(ProcessBuilder processBuilder, IProgressMonitor monitor) {
		MultiStatus out = StatusUtils.newMultistatus("Execute " + StringUtils.join(processBuilder.command(), " "));
		try {
			Process process = processBuilder.start();
			while (ProcessUtils.isProcessRunning(process)) {
				if (monitor.isCanceled()) {
					process.destroy();
				}
			}
			int rc = process.exitValue();
			int severity = rc == 0 ? IStatus.OK : IStatus.ERROR;
			out.add(StatusUtils.newStatus(severity, "Process exited with " + rc));
			StatusUtils.safeAdd(out, streamToStatus("Stdout", severity, process.getInputStream()));
			StatusUtils.safeAdd(out, streamToStatus("Stderr", severity, process.getErrorStream()));
		} catch (IOException e) {
			out.add(StatusUtils.newErrorStatus(e));
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
