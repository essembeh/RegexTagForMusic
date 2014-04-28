package org.essembeh.rtfm.fs.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.BooleanUtils;

public class Attributes {

	private final static String ERROR_MESSAGE_SEPARATOR = ",";
	public final static String EXPORT_KEY = "core:export?";
	public final static String FILEHANDLER_KEY = "core:filehandler";
	public final static String ERROR_KEY = "core:error";
	public final static String DATE_KEY = "core:date";

	private Map<String, String> attributes;

	public Attributes() {
		attributes = new HashMap<String, String>();
	}

	public List<String> sortedKeys() {
		List<String> out = new ArrayList<>(attributes.keySet());
		Collections.sort(out);
		return Collections.unmodifiableList(out);
	}

	public Set<String> keySet() {
		return attributes.keySet();
	}

	public String getValue(String name) {
		return attributes.get(name);
	}

	public String getValue(String name, String defaultValue) {
		String value = getValue(name);
		return value == null ? defaultValue : value;
	}

	public String setValue(String name, String value) {
		return attributes.put(name, value);
	}

	public boolean updateValue(String name, String value) {
		if (attributes.containsKey(name)) {
			attributes.put(name, value);
			return true;
		}
		return false;
	}

	public boolean delete(String name) {
		return attributes.remove(name) != null;
	}

	public boolean contains(String name) {
		return attributes.containsKey(name);
	}

	public int count() {
		return attributes.size();
	}

	public void removeErrors() {
		attributes.remove(ERROR_KEY);
	}

	public void updateError(Exception e) {
		updateError(e == null ? "no message" : e.getMessage());
	}

	public void updateError(String message) {
		if (message != null) {
			String value = getValue(ERROR_KEY);
			if (value != null && value.length() > 0) {
				value += ERROR_MESSAGE_SEPARATOR + message;
			} else {
				value = message;
			}
			setValue(ERROR_KEY, value);
		}
	}

	public String setDate(String date) {
		return setValue(DATE_KEY, date);
	}

	public String getFilehandler() {
		return getValue(FILEHANDLER_KEY);
	}

	public String setFilehandler(String value) {
		return setValue(FILEHANDLER_KEY, value);
	}

	public boolean isExportable() {
		return !BooleanUtils.isFalse(BooleanUtils.toBooleanObject(getValue(EXPORT_KEY)));
	}

	public Set<Entry<String, String>> entrySet() {
		return attributes.entrySet();
	}

	public Attributes copy() {
		Attributes out = new Attributes();
		out.attributes.putAll(attributes);
		return out;
	}

	public void restore(Attributes in) {
		if (this != in && in != null) {
			attributes.clear();
			attributes.putAll(in.attributes);
		}
	}

	public boolean isTrue(String key) {
		return BooleanUtils.isTrue(BooleanUtils.toBooleanObject(getValue(key)));
	}

	public boolean isFalse(String key) {
		return BooleanUtils.isFalse(BooleanUtils.toBooleanObject(getValue(key)));
	}
}
