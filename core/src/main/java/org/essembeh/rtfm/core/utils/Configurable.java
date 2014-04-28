package org.essembeh.rtfm.core.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public abstract class Configurable implements IConfigurable {

	private final List<Pair<String, String>> properties;

	public Configurable() {
		properties = new ArrayList<>();
	}

	@Override
	public final void setProperty(String key, String value) {
		properties.add(ImmutablePair.of(key, value));
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

	protected List<String> getPropertiesByKey(String key) {
		List<String> out = new ArrayList<>();
		for (Pair<String, String> p : properties) {
			if (ObjectUtils.equals(key, p.getKey())) {
				out.add(p.getValue());
			}
		}
		return out;
	}
}
