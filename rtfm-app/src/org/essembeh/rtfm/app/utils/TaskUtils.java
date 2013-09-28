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
package org.essembeh.rtfm.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class TaskUtils {

	public static String FILENAME_KEY = "FILE";

	/**
	 * Used to set Task env. <br/>
	 * If the value is ${foo}, return the value of "foo" attribute of the {@link IResource}
	 * 
	 * @param value
	 * @param resource
	 * @return
	 */
	public static String valuateDynamicEnvironmentVariable(String string, IResource resource) {
		Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher matcher = pattern.matcher(string);
		StringBuffer out = new StringBuffer();
		while (matcher.find()) {
			String key = matcher.group(1);
			String value = FILENAME_KEY.equals(key) ? resource.getFile().getAbsolutePath() : resource.getAttributes().getValue(key, "");
			matcher.appendReplacement(out, value);
		}
		matcher.appendTail(out);
		return out.toString();
	}
}
