package org.essembeh.rtfm.core.utils.version.exceptions;

import org.essembeh.rtfm.core.exception.RTFMException;

public class ReaderException extends RTFMException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public ReaderException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param t
	 */
	public ReaderException(Throwable t) {
		super(t);
	}

}
