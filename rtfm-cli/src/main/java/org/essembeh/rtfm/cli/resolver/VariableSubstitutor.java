package org.essembeh.rtfm.cli.resolver;

import java.nio.file.Path;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang3.text.StrMatcher;
import org.apache.commons.lang3.text.StrSubstitutor;

public class VariableSubstitutor extends StrSubstitutor {

	private static final String PREFIX = "${";
	private static final String SUFFIX = "}";

	private static final StrMatcher PREFIX_MATCHER = StrMatcher.stringMatcher(PREFIX);
	private static final StrMatcher SUFFIX_MATCHER = StrMatcher.stringMatcher(SUFFIX);

	public VariableSubstitutor(Path path, Matcher matcher, Map<String, String> extraVariables, boolean useEnv) {
		this(new VariableResolver(path, matcher, extraVariables, useEnv));
	}

	public VariableSubstitutor(VariableResolver resolver) {
		super(resolver, PREFIX_MATCHER, SUFFIX_MATCHER, '\\');
	}

	public boolean isComplete(String in) {
		return !in.contains(PREFIX);
	}
}
