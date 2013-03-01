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

package org.essembeh.rtfm.core.properties;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ConfigurationException;

import com.google.inject.Inject;

/**
 * 
 * @author seb
 */
public class RTFMPropertiesFromFile implements RTFMProperties {

	/**
	 * The logger.
	 */
	static private Logger logger = Logger.getLogger(RTFMPropertiesFromFile.class);

	Properties properties;

	String propertyFilename;

	@Inject
	public RTFMPropertiesFromFile(String propertyFilename) throws ConfigurationException {
		this.propertyFilename = propertyFilename;
		properties = new Properties();
		try {
			URL resource = Thread.currentThread().getContextClassLoader().getResource(this.propertyFilename);
			properties.load(resource.openStream());
			logger.debug("Read properties: " + this.propertyFilename + ", found: " + properties.entrySet().size() + " items");
		} catch (IOException e) {
			logger.error("Cannot load properties: " + this.propertyFilename);
			logger.error(e);
			throw new ConfigurationException();
		}
	}

	@Override
	public String getMandatoryProperty(String string) throws ConfigurationException {
		String value = getProperty(string);
		if (value == null) {
			throw new ConfigurationException();
		}
		return value;
	}

	@Override
	public String getProperty(String string) {
		return properties.getProperty(string);
	}

	@Override
	public void updateProperty(String name, String value) {
		properties.setProperty(name, value);
	}

	@Override
	public Boolean getBoolean(String name) {
		Boolean out = null;
		String value = getProperty(name);
		if (value != null) {
			out = Boolean.parseBoolean(value);
		}
		return out;
	}

	@Override
	public Integer getInteger(String name) {
		Integer out = null;
		String value = getProperty(name);
		if (value != null) {
			out = Integer.parseInt(value);
		}
		return out;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getWithDefault(String key, T defaultValue) {
		T out = null;
		if (defaultValue instanceof String) {
			out = (T) getProperty(key);
		} else if (defaultValue instanceof Integer) {
			out = (T) getInteger(key);
		} else if (defaultValue instanceof Boolean) {
			out = (T) getBoolean(key);
		}
		return out == null ? defaultValue : out;
	}

	@Override
	public String getSpecialAttribute(SpecialAttribute specialAttribute) {
		return getProperty(specialAttribute.getKey());
	}
}
