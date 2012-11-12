package org.essembeh.rtfm.core.utils.version;

import java.io.InputStream;

import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;

public interface ILoadable {

	/**
	 * 
	 * @param inputStream
	 * @throws ReaderException
	 */
	void load(InputStream inputStream) throws ReaderException;

	/**
	 * 
	 */
	void resetValues();
}
