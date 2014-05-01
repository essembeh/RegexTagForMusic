package org.essembeh.rtfm.exec.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.AttributesHelper;

public class PropertyUtils {

	public static String FILENAME_KEY = "FILE";

	/**
	 * Used to set Task env. <br/>
	 * If the value is ${foo}, return the value of "foo" attribute of the {@link IResource}
	 * 
	 * @param value
	 * @param resource
	 * @return
	 */
	public static String valuateDynamicEnvironmentVariable(String string, IResource resource) {
		Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
		Matcher matcher = pattern.matcher(string);
		StringBuffer out = new StringBuffer();
		while (matcher.find()) {
			String key = matcher.group(1);
			String value = FILENAME_KEY.equals(key) ? resource.getFile().getAbsolutePath() : AttributesHelper.get(
					resource, key, "");
			matcher.appendReplacement(out, value);
		}
		matcher.appendTail(out);
		return out.toString();
	}

	/**
	 * 
	 * @param in
	 * @return
	 */
	public static Pair<String, String> stringToPair(String in) {
		Pair<String, String> out = null;
		if (in != null) {
			int index = in.indexOf("=");
			if (index < 0) {
				out = ImmutablePair.of(in, null);
			} else {
				out = ImmutablePair.of(in.substring(0, index), in.substring(index + 1, in.length()));
			}
		}
		return out;
	}
}
