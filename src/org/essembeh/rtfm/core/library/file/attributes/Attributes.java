package org.essembeh.rtfm.core.library.file.attributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Attributes {

	private Map<String, String> attributes;

	public Attributes() {
		attributes = new ConcurrentHashMap<String, String>();
	}

	public boolean contains(String name) {
		return attributes.containsKey(name);
	}

	public String getAttributeValue(String name) {
		return attributes.get(name);
	}

	public String get(String name, String defaultValue) {
		return contains(name) ? attributes.get(name) : defaultValue;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return true if the attribute exists and has been updated
	 */
	public boolean updateIfExists(String name, String value) {
		boolean exists = contains(name);
		if (exists) {
			attributes.put(name, value);
		}
		return exists;
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @return true if the attribute existed, false if it has been created
	 */
	public boolean updateOrCreate(String name, String value) {
		boolean exists = contains(name);
		attributes.put(name, value);
		return exists;
	}

	public void putAll(Map<String, String> in) {
		for (Entry<String, String> e : in.entrySet()) {
			updateOrCreate(e.getKey(), e.getValue());
		}
	}

	public Set<Entry<String, String>> entrySet() {
		return attributes.entrySet();
	}

	public List<String> getAllNames() {
		ArrayList<String> out = new ArrayList<String>(Arrays.asList(attributes.keySet().toArray(new String[0])));
		Collections.sort(out);
		return out;
	}
}
