package org.essembeh.rtfm.cli.resolver;

import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrMatcher;
import org.apache.commons.lang3.text.StrSubstitutor;

public class VariableUtils {
	public static final StrMatcher PREFIX_MATCHER = StrMatcher.stringMatcher("${");
	public static final StrMatcher SUFFIX_MATCHER = StrMatcher.stringMatcher("}");

	public static StrSubstitutor createResolver(StrLookup<String> lookup) {
		return new StrSubstitutor(lookup, PREFIX_MATCHER, SUFFIX_MATCHER, '\\');
	}
}