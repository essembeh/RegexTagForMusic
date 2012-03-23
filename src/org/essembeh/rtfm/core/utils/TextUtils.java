package org.essembeh.rtfm.core.utils;

public class TextUtils {

	static public String plural(int size, String name) {
		return "" + size + " " + name + (size > 1 ? "s" : "");
	}
}
