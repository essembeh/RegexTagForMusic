package org.essembeh.rtfm.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileUtils2 {

	public static File checkExists(File in) throws FileNotFoundException {
		if (in == null) {
			throw new FileNotFoundException();
		}
		if (!in.exists()) {
			throw new FileNotFoundException(in.getAbsolutePath());
		}
		return in;
	}

	public static File checkFolderExists(File in) throws IOException {
		checkExists(in);
		if (!in.isDirectory()) {
			throw new IOException("Invalid folder: " + in.getAbsolutePath());
		}
		return in;
	}

	public static File checkFileExists(File in) throws IOException {
		checkExists(in);
		if (!in.isFile()) {
			throw new IOException("Invalid file: " + in.getAbsolutePath());
		}
		return in;
	}

	public static List<File> browse(File folder) {
		List<File> out = new ArrayList<>();
		if (folder != null && folder.isDirectory()) {
			File[] ls = folder.listFiles();
			if (ls != null) {
				out.addAll(Arrays.asList(ls));
				Collections.sort(out);
			}
		}
		return out;
	}
}
