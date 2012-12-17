package org.essembeh.rtfm.core.utils.version;

import java.io.InputStream;

import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;

public interface IReader {

	/**
	 * 
	 * @param inputStream
	 * @throws ReaderException
	 */
	void read(InputStream inputStream) throws ReaderException;
}
