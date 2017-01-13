package org.essembeh.rtfm.cli.resolver;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang3.text.StrLookup;

public class VariableResolver extends StrLookup<String> {

	private final Matcher matcher;
	private final boolean useEnv;
	private Map<String, String> extraVariables = new HashMap<>();

	public VariableResolver(Matcher matcher, Map<String, String> extraVariables, boolean useEnv) {
		this.matcher = matcher;
		this.extraVariables.putAll(extraVariables);
		this.useEnv = useEnv;
	}

	@Override
	public String lookup(String key) {
		String out = null;
		try {
			out = matcher.group(key);
		} catch (IllegalArgumentException ignored) {
		}
		if (out == null) {
			out = extraVariables.get(key);
		}
		if (out == null && useEnv) {
			out = System.getenv(key);
		}
		if (out == null) {
			throw new IllegalStateException("Cannot resolve " + key);
		}
		return out;
	}

}
