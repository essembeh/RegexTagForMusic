package org.essembeh.rtfm.core.actions;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public interface IRTFMTask {

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
	public void execute(IMusicFile file) throws ActionException;

}
