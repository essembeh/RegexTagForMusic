package org.essembeh.rtfm.core.utils.version;

import java.io.InputStream;

import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;

public interface IObjectReader<T extends ILoadable> {

	/**
	 * 
	 * @param inputStream
	 * @param element
	 * @throws ReaderException
	 */
	void readObject(InputStream inputStream, T element) throws ReaderException;
}
