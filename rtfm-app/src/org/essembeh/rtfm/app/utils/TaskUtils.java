package org.essembeh.rtfm.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class TaskUtils {

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
			String value = FILENAME_KEY.equals(key) ? resource.getFile().getAbsolutePath() : resource.getAttributes().getValue(key, "");
			matcher.appendReplacement(out, value);
		}
		matcher.appendTail(out);
		return out.toString();
	}
}
