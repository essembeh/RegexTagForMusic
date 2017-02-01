package org.essembeh.rtfm.core.resolver;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import org.apache.commons.lang3.text.StrLookup;

public class VariableResolver extends StrLookup<String> {

	private final Path path;
	private final Matcher matcher;
	private final boolean useEnv;
	private Map<String, String> extraVariables = new HashMap<>();

	public VariableResolver(Path path, Matcher matcher, Map<String, String> extraVariables, boolean useEnv) {
		this.path = path;
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
		if (out == null) {
			out = Stream.of(BuiltinVariables.values()).filter(v -> key.equals(v.getVarName())).findFirst().map(v -> v.resolve(path)).orElse(null);
		}
		if (out == null && useEnv) {
			out = System.getenv(key);
		}
		return out;
	}

}
