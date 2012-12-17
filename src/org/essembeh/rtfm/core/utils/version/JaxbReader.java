package org.essembeh.rtfm.core.utils.version;

import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.core.utils.jaxb.JaxbUtils;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;

public class JaxbReader<T> implements IReader {

	private T model;
	private final Class<T> modelClass;

	/**
	 * 
	 * @param modelClass
	 */
	public JaxbReader(Class<T> modelClass) {
		this.modelClass = modelClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.IReader#read(java.io.InputStream)
	 */
	@Override
	public void read(InputStream inputStream) throws ReaderException {
		model = null;
		try {
			model = JaxbUtils.readJaxbObject(inputStream, modelClass);
		} catch (JAXBException e) {
			throw new ReaderException(e);
		}
	}

	/**
	 * 
	 * @return
	 */
	protected T getModel() {
		return model;
	}
	
	/**
	 * 
	 * @return
	 */
	protected boolean modelAvailable() {
		return model != null;
	}
}
