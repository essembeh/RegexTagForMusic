package org.essembeh.rtfm.core.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringSubstitutor {

	/**
	 * Attributes
	 */
	private final Map<String, String> substitutions;

	/**
	 * 
	 */
	public StringSubstitutor() {
		substitutions = new TreeMap<String, String>();
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void addSubstitution(String key, String value) {
		substitutions.put(key, value);
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public String apply(String input) {
		String out = input;
		if (input != null) {
			for (Entry<String, String> entry : substitutions.entrySet()) {
				out = out.replaceAll(Pattern.quote(entry.getKey()), Matcher.quoteReplacement(entry.getValue()));
			}
		}
		return out;
	}

	/**
	 * 
	 */
	public void clear() {
		substitutions.clear();
	}
}
