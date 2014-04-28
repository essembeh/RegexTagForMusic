package org.essembeh.rtfm.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TextUtils {

	/**
	 * 
	 * @param size
	 * @param name
	 * @return
	 */
	static public String plural(int size, String name) {
		return "" + size + " " + name + (size > 1 ? "s" : "");
	}

	/**
	 * 
	 * @param in
	 * @return
	 */
	static public List<String> toSortedList(Collection<String> in) {
		List<String> out = new ArrayList<>(in);
		Collections.sort(out);
		return Collections.unmodifiableList(out);
	}
}
