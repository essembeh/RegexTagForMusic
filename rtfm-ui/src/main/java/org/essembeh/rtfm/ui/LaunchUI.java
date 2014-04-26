package org.essembeh.rtfm.ui;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.exception.MissingTaskException;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class LaunchUI {

	public static void main(String[] args) throws MissingTaskException, FileNotFoundException, JAXBException {
		Injector injector = Guice.createInjector(new UiModule(args));
		RtfmController controller = injector.getInstance(RtfmController.class);
		controller.loadDefaultConfiguration();
		controller.pack();
		controller.setVisible(true);
	}
}
