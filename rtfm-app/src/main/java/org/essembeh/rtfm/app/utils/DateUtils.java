package org.essembeh.rtfm.app.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static String now() {
		return DATE_FORMAT.format(new Date());
	}
}
