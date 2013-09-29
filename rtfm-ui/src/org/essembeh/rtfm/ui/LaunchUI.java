package org.essembeh.rtfm.ui;

import java.io.FileNotFoundException;

import javax.xml.bind.JAXBException;

import org.essembeh.rtfm.app.exception.UnknownTaskException;

public class LaunchUI {

	public static void main(String[] args) throws UnknownTaskException, FileNotFoundException, JAXBException {
		RtfmController ui = new RtfmController();
		ui.pack();
		ui.setVisible(true);
	}
}
