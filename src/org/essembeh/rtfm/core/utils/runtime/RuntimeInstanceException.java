package org.essembeh.rtfm.core.utils.runtime;

public class RuntimeInstanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7040520042705071833L;

	public RuntimeInstanceException(Throwable t) {
		super(t);
	}

	public RuntimeInstanceException(String string) {
		super(string);
	}
}
