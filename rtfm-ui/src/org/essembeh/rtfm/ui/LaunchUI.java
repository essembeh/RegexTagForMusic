package org.essembeh.rtfm.ui;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.exception.UnknownTaskException;

public class LaunchUI {

	private final static Logger LOGGER = Logger.getLogger(LaunchUI.class);

	public static void main(String[] args) throws UnknownTaskException, FileNotFoundException, JAXBException {
		File defaultConfiguration;
		if (args.length > 0) {
			LOGGER.info("Using custom configurationfile:" + args[0]);
			defaultConfiguration = new File(args[0]);
		} else {
			String homePath = System.getProperty("user.home");
			defaultConfiguration = new File(homePath, ".rtfm/default.xml");
		}
		RtfmController ui = new RtfmController(defaultConfiguration);
		ui.pack();
		ui.setVisible(true);
	}
}
