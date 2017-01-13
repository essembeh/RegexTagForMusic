package org.essembeh.rtfm.model.utils;

import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrLookup;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.rtfm.Node;

public class AttributesUtils {

	public static String entryToString(Map.Entry<String, String> e) {
		return e.getKey() + " = " + e.getValue();
	}

	public static StrSubstitutor newSubstitutor(Node node) {
		return new StrSubstitutor(new StrLookup<String>() {
			@Override
			public String lookup(String arg0) {
				return node.getAttributes().get(arg0);
			}
		});
	}

	public static String appendIfNeeded(String oldValue, String newValue) {
		if (StringUtils.isNotBlank(oldValue)) {
			return oldValue + AttributeConstants.SEPARATOR + newValue;
		}
		return newValue;
	}

	public static void handleError(Node node, Exception e) {
		node.getAttributes().put(AttributeConstants.App.ERROR, appendIfNeeded(node.getAttributes().get(AttributeConstants.App.ERROR), e.getMessage()));
	}

	public static long getLong(Node node, String key) {
		return Long.parseLong(node.getAttributes().get(key));
	}

	public static boolean isTrue(Node node, String key) {
		return BooleanUtils.toBoolean(node.getAttributes().get(key));
	}
}
