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
package org.essembeh.rtfm.starter;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.conf.Configuration;
import org.essembeh.rtfm.core.conf.RTFMProperties;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.frame.MainFrame;
import org.essembeh.rtfm.shell.Shell;
import org.essembeh.rtfm.shell.io.ConsoleInputReader;
import org.essembeh.rtfm.shell.io.ConsoleOutputWriter;
import org.essembeh.rtfm.shell.io.ScriptInputReader;

/**
 * 
 * @author seb
 * 
 */
public class MainClass {

	/**
	 * 
	 */
	static private Logger logger = Logger.getLogger(MainClass.class);

	/**
	 * 
	 */
	private enum Mode {
		SHELL, SCRIPT, GUI
	}

	/**
	 * 
	 * @param args
	 * @throws ConfigurationException
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws ConfigurationException, FileNotFoundException {
		Configuration.init();

		String appMode = null;
		if (args.length > 0) {
			appMode = args[0];
		} else {
			appMode = RTFMProperties.getMandatoryProperty("app.default.mode");
			logger.info("No mod specified, using default: " + appMode);
		}

		Mode mode = null;
		try {
			mode = Mode.valueOf(appMode.toUpperCase());
		} catch (IllegalArgumentException e) {
			logger.error("Unknown mode: " + appMode);
			logger.info("Available modes are: " + StringUtils.arrayToString(Mode.values(), "|"));
		}
		if (mode != null) {
			MusicManager app = new MusicManager();
			switch (mode) {
			case GUI:
				RTFMController controller = new RTFMController(app);
				MainFrame window = new MainFrame(controller.getMainPanel());
				window.setVisible(true);
				break;
			case SCRIPT:
				if (args.length == 2) {
					File script = new File(args[1]);
					logger.debug("Script: " + script.getAbsolutePath());
					Shell shell = new Shell(app, new ScriptInputReader(script), new ConsoleOutputWriter());
					shell.loop();
				} else {
					logger.error("Missing script file argument");
				}
				break;
			case SHELL:
				Shell shell = new Shell(app, new ConsoleInputReader(), new ConsoleOutputWriter());
				shell.loop();
				break;
			}
		}
	}
}
