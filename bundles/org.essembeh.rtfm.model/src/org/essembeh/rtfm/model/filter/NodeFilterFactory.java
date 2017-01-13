package org.essembeh.rtfm.model.filter;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.rtfm.Node;

public class NodeFilterFactory {

	private static final String OPERATOR_EQUALS = "==";
	private static final String OPERATOR_EQUALS_IGNORE_CASE = "=";
	private static final String OPERATOR_MATCHES = "~";
	private static final String OPERATOR_NOT = "!";

	private static final String ID = "(" + AttributeConstants.ATTRIBUTE_NAME_REGEX + ")";
	private static final String VALUE = "(.+)";
	private static final String NOT = "(" + OPERATOR_NOT + ")?";
	private static final String OPERATOR = "(" + OPERATOR_EQUALS + "|" + OPERATOR_EQUALS_IGNORE_CASE + "|" + OPERATOR_MATCHES + ")";

	private static final int SIMPLE_NOT = 1;
	private static final int SIMPLE_ID = 2;
	private static final Pattern FORMAT_SIMPLE = Pattern.compile(NOT + ID);

	private static final int FULL_ID = 1;
	private static final int FULL_NOT = 2;
	private static final int FULL_OPERATOR = 3;
	private static final int FULL_VALUE = 4;
	private static final Pattern FORMAT_FULL = Pattern.compile(ID + NOT + OPERATOR + VALUE);

	public static INodeFilter fromString(String in) throws IllegalArgumentException {
		if (StringUtils.isBlank(in)) {
			throw new IllegalArgumentException("Filter cannot be null");
		}
		Predicate<Node> out = null;
		Matcher matcher;
		if ((matcher = FORMAT_FULL.matcher(in)).matches()) {
			out = fromFullMatcher(matcher);
		} else if ((matcher = FORMAT_SIMPLE.matcher(in)).matches()) {
			out = fromSimpleMatcher(matcher);
		} else {
			throw new IllegalArgumentException("Cannot create filter from " + in);
		}
		return new NodeFilter(out, in);
	}

	private static Predicate<Node> fromSimpleMatcher(Matcher matcher) {
		String attributeName = matcher.group(SIMPLE_ID);
		boolean negate = OPERATOR_NOT.equals(matcher.group(SIMPLE_NOT));
		Predicate<Node> predicate = (n -> n.getAttributes().containsKey(attributeName));
		if (negate) {
			predicate = predicate.negate();
		}
		return predicate;
	}

	private static Predicate<Node> fromFullMatcher(Matcher matcher) {
		String attributeName = matcher.group(FULL_ID);
		String expectedValue = matcher.group(FULL_VALUE);
		String operator = matcher.group(FULL_OPERATOR);
		boolean negate = OPERATOR_NOT.equals(matcher.group(FULL_NOT));

		Predicate<Node> attributeExists = (n -> n.getAttributes().containsKey(attributeName));
		Predicate<Node> predicate = null;
		if (OPERATOR_EQUALS.equals(operator)) {
			predicate = (n -> StringUtils.equals(n.getAttributes().get(attributeName), expectedValue));
		} else if (OPERATOR_EQUALS_IGNORE_CASE.equals(operator)) {
			predicate = (n -> StringUtils.equalsIgnoreCase(n.getAttributes().get(attributeName), expectedValue));
		} else if (OPERATOR_MATCHES.equals(operator)) {
			predicate = (n -> n.getAttributes().containsKey(attributeName) && Pattern.matches(expectedValue, n.getAttributes().get(attributeName)));
		} else {
			throw new IllegalArgumentException("Cannot create filter from " + matcher.group(0));
		}
		if (negate) {
			predicate = predicate.negate();
		}
		return attributeExists.and(predicate);
	}

	public static INodeFilter FOLDER = attributeEquals("file.type", "folder");
	public static INodeFilter FILE = attributeEquals("file.type", "file");
	public static INodeFilter HAS_ERROR = attributeExists(AttributeConstants.App.ERROR);

	public static INodeFilter attributeExists(String name) {
		return fromString(name);
	}

	public static INodeFilter attributeDoesntExist(String name) {
		return fromString(OPERATOR_NOT + name);
	}

	public static INodeFilter attributeEquals(String name, String value) {
		return fromString(name + OPERATOR_EQUALS + value);
	}

	public static INodeFilter attribute(String name, String value) {
		return fromString(name + OPERATOR_NOT + OPERATOR_EQUALS + value);
	}

	public static INodeFilter attributeMatches(String name, String regex) {
		return fromString(name + OPERATOR_MATCHES + regex);
	}

}
