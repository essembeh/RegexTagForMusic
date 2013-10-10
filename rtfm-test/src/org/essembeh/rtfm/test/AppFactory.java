package org.essembeh.rtfm.test;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.Application;
import org.essembeh.rtfm.app.ApplicationModule;
import org.essembeh.rtfm.app.config.RtfmProperties;
import org.essembeh.rtfm.app.exception.MissingTaskException;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class AppFactory {

	public static Application createApplication() throws MissingTaskException, FileNotFoundException, JAXBException {
		Injector injector = Guice.createInjector(new ApplicationModule(new RtfmProperties()));
		Application app = injector.getInstance(Application.class);
		app.loadConfiguration(new File("resources/seb.xml"));
		return app;
	}
}
