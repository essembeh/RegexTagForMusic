package org.essembeh.rtfm.fs.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class AttributesHelper {

	private final static String ERROR_MESSAGE_SEPARATOR = ",";
	public final static String EXPORT_KEY = "core:export?";
	public final static String FILEHANDLER_KEY = "core:filehandler";
	public final static String ERROR_KEY = "core:error";
	public final static String DATE_KEY = "core:date";

	public static List<String> sortedKeys(IResource resource) {
		List<String> out = new ArrayList<>(resource.getAttributes().keySet());
		Collections.sort(out);
		return Collections.unmodifiableList(out);
	}

	public static String get(IResource resource, String name) {
		return resource.getAttributes().get(name);
	}

	public static String get(IResource resource, String name, String defaultValue) {
		String value = get(resource, name);
		return value == null ? defaultValue : value;
	}

	public static String set(IResource resource, String name, String value) {
		return resource.getAttributes().put(name, value);
	}

	public static boolean update(IResource resource, String name, String value) {
		if (resource.getAttributes().containsKey(name)) {
			resource.getAttributes().put(name, value);
			return true;
		}
		return false;
	}

	public static boolean delete(IResource resource, String name) {
		return resource.getAttributes().remove(name) != null;
	}

	public static void removeErrors(IResource resource) {
		delete(resource, ERROR_KEY);
	}

	public static void updateError(IResource resource, Exception e) {
		updateError(resource, e == null ? "no message" : e.getMessage());
	}

	public static void updateError(IResource resource, String message) {
		if (message != null) {
			String value = get(resource, ERROR_KEY);
			if (value != null && value.length() > 0) {
				value += ERROR_MESSAGE_SEPARATOR + message;
			} else {
				value = message;
			}
			set(resource, ERROR_KEY, value);
		}
	}

	public static String setDate(IResource resource, String date) {
		return set(resource, DATE_KEY, date);
	}

	public static String getFilehandler(IResource resource) {
		return get(resource, FILEHANDLER_KEY);
	}

	public static String setFilehandler(IResource resource, String value) {
		return set(resource, FILEHANDLER_KEY, value);
	}

	public static boolean isExportable(IResource resource) {
		return !BooleanUtils.isFalse(BooleanUtils.toBooleanObject(get(resource, EXPORT_KEY)));
	}

	public static boolean isTrue(IResource resource, String key) {
		return BooleanUtils.isTrue(BooleanUtils.toBooleanObject(get(resource, key)));
	}

	public static boolean isFalse(IResource resource, String key) {
		return BooleanUtils.isFalse(BooleanUtils.toBooleanObject(get(resource, key)));
	}

	public static HashMap<String, String> cloneAttributes(IResource resource) {
		return new HashMap<>(resource.getAttributes());
	}

	public static void resetAttributes(IResource resource, Map<String, String> map) {
		resource.getAttributes().clear();
		resource.getAttributes().putAll(map);
	}

}
