package org.essembeh.rtfm.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.fs.util.FileTools;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class ConfigurationTool {

	private final static String RTFM_CONFIG_FOLDER = ".rtfm";
	private final static String RTFM_DEFAULT_CONFIG_FILENAME = "default.xml";
	private final static String EMBEDDED_CONFIG_FILENAME = "default.xml";

	private final String customConfigFilename;

	@Inject
	public ConfigurationTool(@Named("customConfigurationFile") String customConfig) {
		this.customConfigFilename = customConfig;
	}

	public File getCustomConfigurationFile() {
		if (!StringUtils.isEmpty(customConfigFilename)) {
			File f = new File(customConfigFilename);
			if (f.exists() && f.canRead()) {
				return f;
			}
		}
		return null;
	}

	public File getUserConfigurationFile() {
		File out = null;
		File rtfmConfigFolder = new File(System.getProperty("user.home"), RTFM_CONFIG_FOLDER);
		if (rtfmConfigFolder.exists()) {
			File config = new File(rtfmConfigFolder, RTFM_DEFAULT_CONFIG_FILENAME);
			if (config.exists() && config.canRead()) {
				out = config;
			}
		}
		return out;
	}

	public InputStream getEmbeddedConfiguration() throws FileNotFoundException {
		return FileTools.getResourceAsStream(EMBEDDED_CONFIG_FILENAME);
	}
}
