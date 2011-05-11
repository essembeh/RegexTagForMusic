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
import java.util.Properties;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.RTFMException;

/**
 * 
 * @author seb
 */
public class RTFMProperties {

	/**
	 * 
	 */
	static Logger logger = Logger.getLogger(RTFMProperties.class);

	/**
	 * 
	 */
	public static final String RTFM_PROPERTIES = "rtfm.properties";

	/**
	 * Content.
	 */
	protected static Properties properties = null;

	/**
	 * Initialize properties.
	 * 
	 */
	protected static void init() {
		if (properties == null) {
			properties = new Properties();
			try {
				properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(RTFM_PROPERTIES));
				logger.debug("Read properties: " + RTFM_PROPERTIES + ", found: " + properties.entrySet().size()
						+ " items");
			} catch (IOException e) {
				logger.error("Cannot load properties: " + RTFM_PROPERTIES);
				logger.error(e);
				// TODO better way to handle this
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 
	 * @param string
	 * @return
	 * @throws RTFMException
	 */
	public static String getMandatoryProperty(String string) throws ConfigurationException {
		String value = getProperty(string);
		if (value == null) {
			throw new ConfigurationException("The mandatory property cannot be found: " + string);
		}
		return value;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String getProperty(String string) {
		init();
		return properties.getProperty(string);
	}
}
