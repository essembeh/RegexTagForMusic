package org.essembeh.rtfm.app.config;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class RtfmProperties extends Properties {

	private static final long serialVersionUID = -8708650832689594219L;

	private final static String JOB_NB_THREAD = "workflow:job.threads";
	private final static String FS_IGNORE_HIDDEN = "fs:ignore.hidden";
	private final static String UI_HIDDEN_BOOLEAN_ATTRIBUTE = "ui:hidden.attribute";

	public int getThreadCount() {
		int out = 4;
		try {
			String property = getProperty(JOB_NB_THREAD);
			if (StringUtils.isNumeric(property)) {
				out = Integer.parseInt(property);
			}
		} catch (Exception e) {
		}
		return out;
	}

	public boolean ignoreHiddenResources() {
		String property = getProperty(FS_IGNORE_HIDDEN, "false");
		return Boolean.TRUE.toString().equalsIgnoreCase(property);
	}

	public String getUiHiddenAttribute() {
		return getProperty(UI_HIDDEN_BOOLEAN_ATTRIBUTE);
	}
}
