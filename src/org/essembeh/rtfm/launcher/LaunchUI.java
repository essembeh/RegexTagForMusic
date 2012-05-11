/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.launcher;

import org.essembeh.rtfm.core.CoreModule;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.properties.RTFMPropertiesFromFile;
import org.essembeh.rtfm.ui.RtfmUI;
import org.essembeh.rtfm.ui.UIModule;
import org.essembeh.rtfm.ui.controller.MainController;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class LaunchUI {

	public static void main(String[] args) throws ConfigurationException {
		RTFMProperties rtfmProperties = new RTFMPropertiesFromFile("rtfm.properties");
		Injector injector = Guice.createInjector(new CoreModule(rtfmProperties), new UIModule(rtfmProperties));
		MainController controller = injector.getInstance(MainController.class);
		RtfmUI ui = new RtfmUI(controller);
		ui.setVisible(true);
	}
}
