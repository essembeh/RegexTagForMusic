package org.essembeh.rtfm.core.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.essembeh.rtfm.model.configuration.TConfiguration;
import org.essembeh.rtfm.model.configuration.TProperty;

public class Configuration {

	private final TConfiguration model;

	public Configuration(TConfiguration model) {
		this.model = model;
	}
	
	public Map<String, String> getProperties() {
		Map<String, String> out = new HashMap<>();
		for (TProperty prop : model.getProperties().getProperty()) {
			out.put(prop.getName(), prop.getValue());
		}
		return Collections.unmodifiableMap(out);
	}
}
