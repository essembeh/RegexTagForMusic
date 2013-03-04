package org.essembeh.rtfm.core.library.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Attributes {

	private static final String ERROR_SEPARATOR = ", ";
	private Map<String, String> attributes;

	/**
	 * 
	 */
	public Attributes() {
		attributes = new ConcurrentHashMap<String, String>();
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean contains(String name) {
		return name != null && attributes.containsKey(name);
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public String getAttributeValue(String name) {
		return attributes.get(name);
	}

	/**
	 * 
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getAttributeValue(String name, String defaultValue) {
		return contains(name) ? attributes.get(name) : defaultValue;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return true if the attribute has been updated
	 */
	public boolean updateIfExists(String name, String value) {
		boolean out = contains(name) && value != null;
		if (out) {
			attributes.put(name, value);
		}
		return out;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return true if the attribute has been updated or created
	 */
	public boolean updateOrCreate(String name, String value) {
		boolean out = value != null;
		if (out) {
			attributes.put(name, value);
		}
		return out;
	}

	/**
	 * 
	 * @param in
	 */
	public void putAll(Map<String, String> in) {
		for (Entry<String, String> e : in.entrySet()) {
			updateOrCreate(e.getKey(), e.getValue());
		}
	}

	/**
	 * 
	 * @return
	 */
	public Set<Entry<String, String>> entrySet() {
		return attributes.entrySet();
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllNames() {
		ArrayList<String> out = new ArrayList<String>(Arrays.asList(attributes.keySet().toArray(new String[0])));
		Collections.sort(out);
		return out;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return true if the attribute has been updated or created
	 */
	public boolean appendAttributeValue(String name, String value) {
		String previousValue = getAttributeValue(name);
		String newValue = previousValue == null ? value : (previousValue + ERROR_SEPARATOR + value);
		updateOrCreate(name, newValue);
		return previousValue != null;
	}
}
