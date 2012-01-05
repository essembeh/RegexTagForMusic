package org.essembeh.rtfm.core.workflow;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.MusicFile;

public interface Task {

	/**
	 * Configure the service with the given property
	 * 
	 * @param name
	 * @param value
	 */
	void setProperty(String name, String value);

	/**
	 * 
	 * @param file
	 * @throws ActionException
	 */
	public void execute(MusicFile file) throws ActionException;

}
