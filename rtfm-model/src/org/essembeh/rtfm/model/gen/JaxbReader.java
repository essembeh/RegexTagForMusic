package org.essembeh.rtfm.model.gen;

import java.io.InputStream;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.model.gen.configuration.v1.TConfiguration;
import org.essembeh.rtfm.model.gen.filesystem.v1.TProject;

public class JaxbReader {

	public static TConfiguration readConfiguration(InputStream inputStream) throws JAXBException {
		TConfiguration out = JaxbUtils.readJaxbObject(inputStream, TConfiguration.class);
		return out;
	}

	public static TProject readProject(InputStream inputStream) throws JAXBException {
		TProject out = JaxbUtils.readJaxbObject(inputStream, TProject.class);
		return out;
	}
}
