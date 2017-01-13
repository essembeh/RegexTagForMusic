package org.essembeh.rtfm.tests.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IStatus;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.AttributesUtils;

public class SysoutUtils {

	private static final Map<Integer, String> SEVERITIES = new HashMap<>();

	static {
		SEVERITIES.put(IStatus.OK, "OK");
		SEVERITIES.put(IStatus.INFO, "INFO");
		SEVERITIES.put(IStatus.WARNING, "WARNING");
		SEVERITIES.put(IStatus.ERROR, "ERROR");
		SEVERITIES.put(IStatus.CANCEL, "CANCEL");
	}

	public static void dumpNode(Node in) {
		System.out.println(in.getVirtualPath());
	}

	public static void dumpNodeWithAttributes(Node in) {
		System.out.println(in.getVirtualPath());
		for (Entry<String, String> e : in.getAttributes()) {
			System.out.println("    " + AttributesUtils.entryToString(e));
		}
	}

	public static IStatus dumpStatus(IStatus s) {
		return dumpStatus(s, System.out::println, 0);
	}

	public static IStatus dumpStatus(IStatus s, Consumer<String> out) {
		return dumpStatus(s, out, 0);
	}

	private static IStatus dumpStatus(IStatus s, Consumer<String> out, int indent) {
		String severity = StringUtils.defaultString(SEVERITIES.get(s.getSeverity()), "" + s.getSeverity());
		try {
			for (String line : IOUtils.readLines(new StringReader(s.getMessage()))) {
				String message = String.format("[%s] %s", severity, line);
				out.accept(StringUtils.repeat("  ", indent) + message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (IStatus ss : s.getChildren()) {
			dumpStatus(ss, out, indent + 1);
		}
		return s;
	}
}
