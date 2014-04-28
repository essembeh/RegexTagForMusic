package org.essembeh.rtfm.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.core.Application;
import org.essembeh.rtfm.core.ApplicationModule;
import org.essembeh.rtfm.core.exception.MissingTaskException;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class AppFactory {

	public static Application createApplication() throws MissingTaskException, FileNotFoundException, JAXBException {
		Injector injector = Guice.createInjector(new ApplicationModule());
		Application app = injector.getInstance(Application.class);
		app.loadConfiguration(new FileInputStream(new File("resources/seb/xml")));
		return app;
	}
}
