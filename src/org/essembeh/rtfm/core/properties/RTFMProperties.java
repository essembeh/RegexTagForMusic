package org.essembeh.rtfm.core.properties;

import org.essembeh.rtfm.core.exception.ConfigurationException;

public interface RTFMProperties {

	public String getMandatoryProperty(String string) throws ConfigurationException;

	public String getProperty(String string);

	public boolean getBoolean(String name);

	public void updateProperty(String name, String value);

}
