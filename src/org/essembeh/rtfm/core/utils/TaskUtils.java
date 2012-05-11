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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.tasks.ITask;

public class TaskUtils {

	private static final Logger logger = Logger.getLogger(TaskUtils.class);

	/**
	 * 
	 * @param identifier
	 * @return
	 * @throws TaskException
	 */
	public static ITask instantiateTask(String identifier) throws TaskException {
		logger.debug("Instantiate class: " + identifier);
		ITask task = null;
		try {
			Class<?> clazz = Class.forName(identifier);
			task = (ITask) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new TaskException(e);
		} catch (InstantiationException e) {
			throw new TaskException(e);
		} catch (IllegalAccessException e) {
			throw new TaskException(e);
		}
		return task;
	}

	/**
	 * Used to set Task env. <br/>
	 * If the value is ${foo}, return the value of "foo" attribute of the
	 * {@link MusicFile}
	 * 
	 * @param value
	 * @param musicFile
	 * @return
	 */
	public static String valuateDynamicEnvironmentVariable(String value, IMusicFile musicFile) {
		String attributeValue = value;
		Pattern pattern = Pattern.compile("\\$\\{(.*)\\}");
		Matcher matcher = pattern.matcher(value);
		if (matcher.matches()) {
			String attributeName = matcher.group(1);
			logger.debug("Dynamic value: " + value + ", attribute name: " + attributeName);
			Attribute attribute = musicFile.getAttributeList().get(attributeName);
			if (attribute != null) {
				attributeValue = attribute.getValue();
				logger.debug("Found value: " + attributeValue);
			} else {
				attributeValue = "";
				logger.warn("Cannot find attribute: " + attributeName + ", on file: " + musicFile);
			}
		}
		return attributeValue;
	}
}