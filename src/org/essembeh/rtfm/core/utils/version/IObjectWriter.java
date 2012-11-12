package org.essembeh.rtfm.core.utils.version;

import java.io.OutputStream;

import org.essembeh.rtfm.core.utils.version.exceptions.WriterException;

public interface IObjectWriter<T extends ISaveable> {

	/**
	 * 
	 * @param outputStream
	 * @param element
	 * @throws WriterException
	 */
	void writeObject(OutputStream outputStream, T element) throws WriterException;
}
