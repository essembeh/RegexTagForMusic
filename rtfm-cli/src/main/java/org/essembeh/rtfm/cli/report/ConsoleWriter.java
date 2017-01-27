package org.essembeh.rtfm.cli.report;

import java.nio.file.Path;

import org.apache.commons.codec.digest.DigestUtils;
import org.essembeh.rtfm.cli.utils.ResourceUtils;

public class ConsoleWriter {

	public static ConsoleWriter create(Path in, boolean displayId) {
		if (displayId) {
			String id = DigestUtils.sha1Hex(ResourceUtils.getFullPath(in));
			return new ConsoleWriter(id.substring(0, 7) + " ");
		}
		return new ConsoleWriter("");
	}

	private final String prefix;

	public ConsoleWriter(String prefix) {
		this.prefix = prefix;
	}

	protected void printLine(String format, Object... args) {
		System.out.println(new StringBuilder(prefix).append(String.format(format, args)));
	}
}
