package org.essembeh.rtfm.core.utils.version.exceptions;

import org.essembeh.rtfm.core.exception.RTFMException;

public class WriterException extends RTFMException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public WriterException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param t
	 */
	public WriterException(Throwable t) {
		super(t);
	}

}
