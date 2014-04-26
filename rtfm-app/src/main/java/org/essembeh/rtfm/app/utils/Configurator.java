package org.essembeh.rtfm.app.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public abstract class Configurator<T extends IConfigurable> implements IConfigurable {

	private final List<Pair<String, String>> configuration;

	public Configurator() {
		this.configuration = new ArrayList<>();
	}

	@Override
	public void setProperty(String key, String value) {
		configuration.add(ImmutablePair.of(key, value));
	}

	public T getConfiguredObject(T in) {
		T out = in;
		for (Pair<String, String> p : configuration) {
			out.setProperty(p.getKey(), p.getValue());
		}
		return out;
	}
}
