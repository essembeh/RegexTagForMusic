package org.essembeh.rtfm.cli.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceUtils {

	public static String getFullPath(Path in) {
		String out = in.toAbsolutePath().normalize().toString();
		if (Files.isDirectory(in)) {
			out += File.separator;
		}
		return out;
	}
}
