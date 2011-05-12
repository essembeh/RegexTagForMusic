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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author seb
 * 
 */
public class StringUtils {

	/**
	 * 
	 */
	static protected Logger logger = Logger.getLogger(StringUtils.class);

	/**
	 * 
	 * @param str
	 * @param list
	 */
	public static void appendStringsToList(String str, List<String> list) {
		if (str != null) {
			if (str.contains(" ")) {
				String[] array = str.split(" ");
				for (String string : array) {
					list.add(string);
				}
			} else {
				list.add(str);
			}
		}
	}

	/**
	 * 
	 * @param list
	 * @param delimiter
	 * @return
	 */
	public static String arrayToString(Object[] array, String delimiter) {
		StringBuilder sb = new StringBuilder();
		if (array.length > 0) {
			sb.append(array[0]);
			if (array.length > 1) {
				for (int i = 1; i < array.length; i++) {
					sb.append(delimiter).append(array[i]);
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * @param str
	 * @param count
	 * @return
	 */
	public static String plural(String str, int count) {
		if (count > 1) {
			str += "s";
		}
		return str;
	}

	/**
	 * 
	 * @param line
	 * @param separator
	 * @return
	 */
	static public List<String> stringToList(String line, String separator) {
		List<String> list = new ArrayList<String>();
		if (line != null) {
			if (line.contains(separator)) {
				String[] split = line.split(separator);
				for (String string : split) {
					list.add(string);
				}
			} else {
				list.add(line);
			}
		}
		return list;
	}
}
