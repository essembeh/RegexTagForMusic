package org.essembeh.rtfm.core.utils.version;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.utils.version.exceptions.NoReaderException;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;

public class MultiObjectReader<T extends ILoadable> implements IObjectReader<T> {

	/**
	 * Attributes
	 */
	private final static Logger logger = Logger.getLogger(MultiObjectReader.class);
	private final List<IObjectReader<T>> readers;

	/**
	 * 
	 */
	public MultiObjectReader() {
		readers = new ArrayList<IObjectReader<T>>();
	}

	/**
	 * 
	 * @param reader
	 */
	public void addreader(IObjectReader<T> reader) {
		readers.add(reader);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.IObjectReader#readObject(java.io.InputStream, org.essembeh.rtfm.core.utils.version.ILoadable)
	 */
	@Override
	public void readObject(InputStream inputStream, T element) throws ReaderException {
		boolean readerFound = false;
		for (IObjectReader<T> reader : readers) {
			logger.debug("trying reader: " + reader);
			try {
				reader.readObject(inputStream, element);
				readerFound = true;
				break;
			} catch (ReaderException e) {
				element.resetValues();
				logger.debug("Invalid reader: " + e.getMessage());

			}
		}
		if (!readerFound) {
			throw new NoReaderException(element);
		}
	}
}
