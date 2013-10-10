package org.essembeh.rtfm.app.config;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class RtfmProperties extends Properties {

	private static final long serialVersionUID = -8708650832689594219L;

	private final static Logger LOGGER = Logger.getLogger(RtfmProperties.class);

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
			LOGGER.debug("Cannot read nb thread in properties", e);
		}
		LOGGER.info("Nb thread: " + out);
		return out;
	}

	public boolean ignoreHiddenResources() {
		String property = getProperty(FS_IGNORE_HIDDEN, "false");
		boolean out = Boolean.TRUE.toString().equalsIgnoreCase(property);
		LOGGER.info("Ignore hidden resources: " + property + " --> " + out);
		return out;
	}

	public String getUiHiddenAttribute() {
		String out = getProperty(UI_HIDDEN_BOOLEAN_ATTRIBUTE);
		LOGGER.info("UI Hidden resource attribute: " + out);
		return out;
	}
}
