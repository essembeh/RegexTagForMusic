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
import java.io.IOException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.conf.Configuration;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.shell.Shell;

public class MainClass {

	static Logger logger = Logger.getLogger(MainClass.class);

	enum Mode {
		SHELL, SCRIPT, GUI
	}

	public static void main(String[] args) throws ConfigurationException {
		MusicManager app = new MusicManager();

		String arg0Mode = null;
		String arg1File = null;
		if (args.length > 0) {
			arg0Mode = args[0];
		} else {
			try {
				arg0Mode = Configuration.instance().getMandatoryProperty("app.default.mode");
				logger.info("No mod specified, using default: " + arg0Mode);
			} catch (ConfigurationException e) {
				logger.error(e.getMessage());
			}
		}

		if (arg0Mode != null) {
			if (args.length > 1) {
				arg1File = args[1];
				logger.debug("Using scriptfile: " + arg1File);
			}

			logger.info("Using mode: " + arg0Mode);
			Mode mode = null;
			try {
				mode = Mode.valueOf(arg0Mode.toUpperCase());
			} catch (IllegalArgumentException e) {
				logger.error("Unknown mode: " + arg0Mode);
				logger.info("Available modes are: " + StringUtils.arrayToString(Mode.values(), "|"));
			}
			if (mode != null) {
				switch (mode) {
				case GUI:
					logger.error("Not yet implemented");
					break;
				case SCRIPT:
					if (arg1File == null) {
						logger.error("Missing script file argument");
					} else {
						Shell shell = new Shell(app);
						try {
							shell.runScriptFile(new File(arg1File));
						} catch (IOException e) {
							logger.error(e.getMessage());
						}
					}
					break;
				case SHELL:
					Shell shell = new Shell(app);
					if (arg1File != null) {
						try {
							shell.runScriptFile(new File(arg1File));
						} catch (IOException e) {
							logger.error(e.getMessage());
						}
					}
					try {
						shell.runInteractive();
					} catch (IOException e) {
						logger.error(e.getMessage());
					}
					break;
				}
			}
		}
	}
}
