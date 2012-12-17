package org.essembeh.rtfm.core.utils.version;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.utils.version.exceptions.NoReaderException;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;

public class MultiReader<T extends IReader> implements IReader {

	/**
	 * Attributes
	 */
	private final static Logger logger = Logger.getLogger(MultiReader.class);
	private final List<T> readers;
	private T lastReader;

	/**
	 * 
	 */
	public MultiReader() {
		readers = new ArrayList<T>();
		lastReader = null;
	}

	/**
	 * 
	 * @param reader
	 */
	public void addReader(T reader) {
		readers.add(reader);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version2.ILoader#load(java.io.InputStream)
	 */
	@Override
	public void read(InputStream inputStream) throws ReaderException {
		lastReader = null;
		for (T loader : readers) {
			logger.debug("trying loader: " + loader);
			try {
				loader.read(inputStream);
				lastReader = loader;
				break;
			} catch (ReaderException e) {
				logger.debug("Invalid reader: " + e.getMessage());

			}
		}
		if (lastReader == null) {
			throw new NoReaderException();
		}
	}

	/**
	 * 
	 * @return
	 */
	protected T getLastReader() {
		return lastReader;
	}
}
