package org.essembeh.rtfm.core.utils.version;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;

public abstract class JaxbObjectReader<LOADABLE extends ILoadable, JAXBMODELCLASS> implements IObjectReader<LOADABLE> {

	/**
	 * 
	 */
	private final static Logger logger = Logger.getLogger(JaxbObjectReader.class);
	private final Class<JAXBMODELCLASS> jaxbModelClass;

	/**
	 * 
	 * @param jaxbPackage
	 */
	public JaxbObjectReader(Class<JAXBMODELCLASS> jaxbModelClass) {
		this.jaxbModelClass = jaxbModelClass;
	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 * @throws JAXBException
	 */
	protected JAXBMODELCLASS readJaxb(InputStream inputStream) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(jaxbModelClass.getPackage().getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setEventHandler(new DefaultValidationEventHandler());
		return unmarshaller.unmarshal(new StreamSource(inputStream), jaxbModelClass).getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.IObjectReader#readObject(java.io.InputStream, org.essembeh.rtfm.core.utils.version.ILoadable)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void readObject(InputStream inputStream, LOADABLE element) throws ReaderException {
		try {
			Object model = readJaxb(inputStream);
			if (model != null && jaxbModelClass.isInstance(model)) {
				readObjectFromModel((JAXBMODELCLASS) model, element);
			}
		} catch (JAXBException e) {
			logger.debug("Error jaxb on current reader", e);
			throw new ReaderException("Invalid Jaxb reader: " + e.toString());
		}
	}

	/**
	 * 
	 * @param model
	 * @param element
	 * @throws ReaderException
	 */
	abstract protected void readObjectFromModel(JAXBMODELCLASS model, LOADABLE element) throws ReaderException;

}
