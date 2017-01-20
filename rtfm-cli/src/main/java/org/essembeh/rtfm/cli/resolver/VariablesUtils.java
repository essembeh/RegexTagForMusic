package org.essembeh.rtfm.cli.resolver;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrMatcher;
import org.apache.commons.lang3.text.StrSubstitutor;

public class VariablesUtils {

	private static final String PREFIX = "${";
	private static final String SUFFIX = "}";

	public static final char ESCAPTE = '\\';
	public static final StrMatcher PREFIX_MATCHER = StrMatcher.stringMatcher(PREFIX);
	public static final StrMatcher SUFFIX_MATCHER = StrMatcher.stringMatcher(SUFFIX);

	public static StrSubstitutor createVariableResolver(Path path, Matcher matcher, Map<String, String> extraVariables, boolean useEnv) {
		return createVariableResolver(new VariableResolver(path, matcher, extraVariables, useEnv));
	}

	public static StrSubstitutor createVariableResolver(StrLookup<String> lookup) {
		return new StrSubstitutor(lookup, PREFIX, SUFFIX, ESCAPTE);
	}

	public static String checkUnresolved(String in, String commandId) {
		List<String> unresolved = new ArrayList<>();
		createVariableResolver(new StrLookup<String>() {
			@Override
			public String lookup(String key) {
				unresolved.add(key);
				return null;
			}
		}).replace(in);
		if (!unresolved.isEmpty()) {
			throw new IllegalStateException(String.format("Cannot resolve %s in command %s", unresolved, commandId));
		}
		return in;
	}
}
