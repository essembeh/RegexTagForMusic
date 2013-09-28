package org.essembeh.rtfm.fs.util;

public class ArrayTools {

	public final static String[] EMPTY_ARRAY = {};

	public static String[] concat(String[] array, String element) {
		String[] out = new String[array.length + 1];
		System.arraycopy(array, 0, out, 0, array.length);
		out[array.length] = element;
		return out;
	}

	public static String[] concat(String element, String[] array) {
		String[] out = new String[array.length + 1];
		System.arraycopy(array, 0, out, 1, array.length);
		out[0] = element;
		return out;
	}

	public static String[] concat(String[] array1, String[] array2) {
		String[] out = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, out, 0, array1.length);
		System.arraycopy(array2, 0, out, array1.length, array2.length);
		return out;
	}

	public static String[] removeFirst(String[] array) {
		if (array.length > 1) {
			String[] out = new String[array.length - 1];
			System.arraycopy(array, 1, out, 0, out.length);
			return out;
		}
		return EMPTY_ARRAY;
	}

	public static String[] removeLast(String[] array) {
		if (array.length > 1) {
			String[] out = new String[array.length - 1];
			System.arraycopy(array, 0, out, 0, out.length);
			return out;
		}
		return EMPTY_ARRAY;
	}

	public static String getFirst(String[] array) {
		return array.length == 0 ? null : array[0];
	}

	public static String getLast(String[] array) {
		return array.length == 0 ? null : array[array.length - 1];
	}
}
