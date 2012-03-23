package org.essembeh.rtfm.core.actions;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.TaskException;

public class TaskUtils {

	private static Logger logger = Logger.getLogger(TaskUtils.class);

	public static IRTFMTask instantiateTask(String identifier) throws TaskException {
		logger.debug("Instantiate class: " + identifier);
		IRTFMTask task = null;
		try {
			Class<?> clazz = Class.forName(identifier);
			task = (IRTFMTask) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			throw new TaskException(e);
		} catch (InstantiationException e) {
			throw new TaskException(e);
		} catch (IllegalAccessException e) {
			throw new TaskException(e);
		}
		return task;
	}
}