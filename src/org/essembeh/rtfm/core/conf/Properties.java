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

package org.essembeh.rtfm.core.conf;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.RTFMException;

/**
 * 
 * @author seb
 * @deprecated
 */
@Deprecated
public class Properties {

	protected final static String PROPERTIES_FILE = "rtfm.properties";

	static Logger logger = Logger.getLogger(Properties.class);

	/** SINGLETON **/
	protected static Properties instance = null;

	/**
	 * Get a specific property
	 * 
	 * @param string
	 * @return
	 * @throws RTFMException
	 */
	static public String get(String string) throws RTFMException {
		String value = getInstance().propertiesFile.getProperty(string);
		logger.debug("Read property: " + string + "=" + value);
		return value;
	}

	/**
	 * 
	 * @return
	 * @throws RTFMException
	 */
	static public String getCheckerClassname() throws RTFMException {
		return get("checker.implementation");
	}

	/**
	 * 
	 * @return
	 * @throws RTFMException
	 */
	public static String getHandlersConfigFile() throws RTFMException {
		return get("handlers.configfile");
	}

	/**
	 * 
	 * @return
	 * @throws RTFMException
	 */
	protected static Properties getInstance() throws RTFMException {
		if (instance == null) {
			instance = new Properties(PROPERTIES_FILE);
		}
		return instance;
	}

	/**
	 * 
	 * @return
	 * @throws RTFMException
	 */
	static public String getReaderClassname() throws RTFMException {
		return get("reader.implementation");
	}

	/**
	 * 
	 * @return
	 * @throws RTFMException
	 */
	public static String getShellConfigFile() throws RTFMException {
		return get("shell.configfile");
	}

	/**
	 * 
	 * @return
	 * @throws RTFMException
	 */
	static public String getTaggerClassname() throws RTFMException {
		return get("tagger.implementation");
	}

	protected java.util.Properties propertiesFile;

	/**
	 * Constructor
	 * 
	 * @throws RTFMException
	 */
	protected Properties(String filename) throws RTFMException {
		this.propertiesFile = new java.util.Properties();

		try {
			this.propertiesFile.load(ClassLoader.getSystemClassLoader().getResourceAsStream(filename));
		} catch (IOException e) {
			logger.error("Cannot read properties file: " + filename);
			throw new RTFMException(e);
		}
	}
}
