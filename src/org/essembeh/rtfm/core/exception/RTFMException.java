package org.essembeh.rtfm.core.exception;

public abstract class RTFMException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RTFMException(String message) {
		super(message);
	}
	public RTFMException(Throwable t) {
		super(t);
	}
}
