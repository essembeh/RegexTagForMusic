/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.essembeh.rtfm.core.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author seb
 * 
 */
public class FileUtils {

	/**
	 * 
	 */
	static protected Logger logger = Logger.getLogger(FileUtils.class);

	static final String SEPARATOR = "/";

	/**
	 * 
	 * @param folder
	 * @param listHidden
	 * @return
	 */
	public static List<File> searchFilesInFolder(File folder, boolean listHidden) {
		List<File> list = new ArrayList<File>();
		if (folder.isDirectory() && folder.exists()) {
			list.add(folder);
			File[] ls = folder.listFiles();
			for (File file : ls) {
				if (file.isFile() && (listHidden || (!file.isHidden()))) {
					list.add(file);
				} else if (file.isDirectory() && (listHidden || (!file.isHidden()))) {
					list.addAll(searchFilesInFolder(file, listHidden));
				}
			}
		}
		return list;
	}

	/**
	 * Make standard path, a Unix path.
	 * 
	 * @param path
	 * @return
	 */
	public static String makeNormalPath(String path) {
		String cleanPath = path.replace("\\", SEPARATOR);
		return cleanPath;
	}

	/**
	 * 
	 * @param child
	 * @param parent
	 * @return
	 */
	public static String extractRelativePath(File child, File parent) {
		byte[] parentPath = parent.getAbsolutePath().getBytes();
		byte[] childPath = child.getAbsolutePath().getBytes();
		int i = 0;
		while (i < parentPath.length && i < childPath.length) {
			if (parentPath[i] != childPath[i]) {
				break;
			}
			i++;
		}
		String relative = makeNormalPath(child.getAbsolutePath().substring(i));
		if (child.isDirectory() && !relative.endsWith(SEPARATOR)) {
			relative += SEPARATOR;
		}
		return relative;
	}

	public static File getResourceAsFile(String filename) throws FileNotFoundException {
		URL resource = Thread.currentThread().getContextClassLoader().getResource(filename);
		if (resource == null) {
			throw new FileNotFoundException("Cannot find file: " + filename);
		}
		return new File(resource.getFile());
	}

	public static InputStream getResourceAsStream(String filename) throws FileNotFoundException {
		InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		if (resource == null) {
			throw new FileNotFoundException("Cannot find file: " + filename);
		}
		return resource;
	}
}
