package org.essembeh.rtfm.model.utils;

import java.util.Arrays;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.essembeh.rtfm.model.RtfmModelActivator;

public class StatusUtils {

	public static IStatus newStatus(int severity, String message, Exception e) {
		return new Status(severity, RtfmModelActivator.PLUGIN_ID, message, e);
	}

	public static IStatus newStatus(int severity, String message) {
		return new Status(severity, RtfmModelActivator.PLUGIN_ID, message);
	}

	public static IStatus newErrorStatus(Exception e) {
		return new Status(IStatus.ERROR, RtfmModelActivator.PLUGIN_ID, e.getMessage(), e);
	}

	public static MultiStatus newMultistatus(String message) {
		MultiStatus out = new MultiStatus(RtfmModelActivator.PLUGIN_ID, 0, message, null);
		return out;
	}

	public static MultiStatus safeAdd(MultiStatus in, IStatus s) {
		if (s != null) {
			in.add(s);
		}
		return in;
	}

	public static Stream<IStatus> recursiveStream(IStatus in) {
		return Stream.concat(Stream.of(in), Arrays.asList(in.getChildren()).stream().flatMap(StatusUtils::recursiveStream));
	}

	public static boolean hasNoError(IStatus s) {
		return s.getSeverity() <= IStatus.INFO;
	}

	public static boolean hasError(IStatus s) {
		return !hasNoError(s);
	}

}
