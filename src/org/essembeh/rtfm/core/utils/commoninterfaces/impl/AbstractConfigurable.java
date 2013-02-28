package org.essembeh.rtfm.core.utils.commoninterfaces.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.essembeh.rtfm.core.utils.commoninterfaces.IConfigurable;

public abstract class AbstractConfigurable implements IConfigurable {

	private final Map<String, String> properties;

	public AbstractConfigurable() {
		properties = new ConcurrentHashMap<String, String>();
	}

	@Override
	public final void setProperty(String key, String value) {
		properties.put(key, value);
	}

	protected Map<String, String> getProperties() {
		return properties;
	}

}
