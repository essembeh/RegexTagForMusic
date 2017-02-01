package org.essembeh.rtfm.core.report;

import java.io.PrintStream;
import java.nio.file.Path;
import java.text.MessageFormat;

import org.apache.commons.codec.digest.DigestUtils;
import org.essembeh.rtfm.core.utils.ResourceUtils;

public class ConsoleWriter {

	public static final PrintStream DEFAULT_STREAM = System.out;

	public static ConsoleWriter create(Path in, boolean displayId) {
		if (displayId) {
			String id = DigestUtils.sha1Hex(ResourceUtils.getFullPath(in));
			return new ConsoleWriter(id.substring(0, 7) + " ", DEFAULT_STREAM);
		}
		return new ConsoleWriter("", DEFAULT_STREAM);
	}

	private final String prefix;
	private final PrintStream stream;

	public ConsoleWriter(String prefix, PrintStream stream) {
		this.prefix = prefix;
		this.stream = stream;
	}

	protected void print(String message) {
		if (message != null) {
			stream.println(prefix + message);
		}
	}

	protected void printMessage(String messageFormat, Object... args) {
		String message = messageFormat;
		if (args.length > 0) {
			try {
				message = MessageFormat.format(messageFormat, args);
			} catch (IllegalArgumentException ignored) {
			}
		}
		print(message);
	}
}
