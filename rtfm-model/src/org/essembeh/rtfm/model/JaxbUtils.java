package org.essembeh.rtfm.model;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

public class JaxbUtils {

	/**
	 * 
	 * @param inputStream
	 * @param jaxbModelClass
	 * @return
	 * @throws JAXBException
	 */
	public static <T> T readJaxbObject(InputStream inputStream, Class<T> jaxbModelClass) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(jaxbModelClass.getPackage().getName());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		unmarshaller.setEventHandler(new DefaultValidationEventHandler());
		return unmarshaller.unmarshal(new StreamSource(inputStream), jaxbModelClass).getValue();
	}

	/**
	 * 
	 * @param outputStream
	 * @param jaxbElement
	 * @param modelClass
	 * @throws JAXBException
	 */
	public static void writeJaxbObject(OutputStream outputStream, JAXBElement<?> jaxbElement, Class<?> modelClass) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(modelClass.getPackage().getName());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(jaxbElement, outputStream);
	}
}
