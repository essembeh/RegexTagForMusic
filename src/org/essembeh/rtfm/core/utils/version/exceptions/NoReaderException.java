package org.essembeh.rtfm.core.utils.version.exceptions;


public class NoReaderException extends ReaderException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 986717568143737129L;

	/**
	 * 
	 * @param element
	 */
	public NoReaderException(Object element) {
		super("No reader for element: " + element);
	}


}
