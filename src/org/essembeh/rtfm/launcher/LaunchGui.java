package org.essembeh.rtfm.launcher;

import org.essembeh.rtfm.core.CoreModule;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.properties.RTFMPropertiesFromFile;
import org.essembeh.rtfm.gui.GuiModule;
import org.essembeh.rtfm.gui.controller.GuiController;
import org.essembeh.rtfm.gui.frame.MainFrame;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class LaunchGui {

	public static void main(String[] args) throws ConfigurationException {
		RTFMProperties rtfmProperties = new RTFMPropertiesFromFile("rtfm.properties");
		CoreModule coreModule = new CoreModule(rtfmProperties);
		GuiModule guiModule = new GuiModule(rtfmProperties);
		Injector injector = Guice.createInjector(coreModule, guiModule);
		GuiController controller = injector.getInstance(GuiController.class);
		MainFrame window = new MainFrame(controller.getMainPanel());
		window.setVisible(true);
	}
}
