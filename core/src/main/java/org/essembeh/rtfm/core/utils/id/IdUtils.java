package org.essembeh.rtfm.core.utils.id;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;

public class IdUtils {

	public static List<String> getIds(List<? extends Identifiable> identifiables) {
		List<String> out = new ArrayList<>();
		for (Identifiable i : identifiables) {
			out.add(i.getId());
		}
		return out;
	}

	public static <T extends Identifiable> Map<String, T> toMap(List<T> in) {
		Map<String, T> out = new TreeMap<>();
		for (T t : in) {
			out.put(t.getId(), t);
		}
		return out;
	}

	public static <T extends Identifiable> T getById(List<T> list, String id) {
		for (T t : list) {
			if (StringUtils.equals(t.getId(), id)) {
				return t;
			}
		}
		return null;
	}
}
