package org.essembeh.rtfm.app.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.Pair;

public abstract class AbstractConfigurable implements IConfigurable {

	private final List<Pair<String, String>> properties;

	public AbstractConfigurable() {
		properties = new ArrayList<>();
	}

	@Override
	public final void setProperty(String key, String value) {
		properties.add(Pair.of(key, value));
	}

	protected List<Pair<String, String>> getProperties() {
		return Collections.unmodifiableList(properties);
	}

	protected String getFirstProperty(String name) {
		for (Pair<String, String> p : properties) {
			if (ObjectUtils.equals(name, p.getKey())) {
				return p.getValue();
			}
		}
		return null;
	}

}
