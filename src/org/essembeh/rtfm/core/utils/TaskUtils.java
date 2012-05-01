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
