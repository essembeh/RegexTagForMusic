package org.essembeh.rtfm.tasks;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public interface ITask {

	/**
	 * Configure the task with a property.
	 * 
	 * @param key
	 * @param value
	 */
	void setProperty(String key, String value);

	/**
	 * Execute the task
	 * 
	 * @param file
	 * @throws ActionException
	 */
	void execute(IMusicFile file) throws ActionException;

}
