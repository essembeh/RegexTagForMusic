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

	/**
	 * 
	 * @param folder
	 * @param listHidden
	 * @return
	 */
	public static List<File> searchFilesInFolder(File folder, boolean listHidden) {
		logger.debug("Search files in folder: " + folder.getAbsolutePath());
		List<File> list = new ArrayList<File>();
		if (folder.isDirectory() && folder.exists()) {
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
		String cleanPath = path.replace("\\", "/");
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
		return child.getAbsolutePath().substring(i);
	}

}
