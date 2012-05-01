package org.essembeh.rtfm.core.properties;

import org.essembeh.rtfm.core.exception.ConfigurationException;

public interface RTFMProperties {

	public String getMandatoryProperty(String string) throws ConfigurationException;

	public String getProperty(String string);

	public Boolean getBoolean(String name);

	public Integer getInteger(String name);

	public void updateProperty(String name, String value);

}
