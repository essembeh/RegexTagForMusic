package org.essembeh.rtfm.core.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.FileUtils;

import com.google.inject.Inject;

/**
 * 
 */
public class ConfigurationHelper {
	private static final Logger logger = Logger.getLogger(ConfigurationHelper.class);

	/**
	 * Attributes
	 */
	private final String defaultConfiguration;

	/**
	 * 
	 * @param properties
	 */
	@Inject
	public ConfigurationHelper(RTFMProperties properties) {
		defaultConfiguration = properties.getProperty("default.configuration");
		logger.debug("Default configuration: " + defaultConfiguration);
	}

	/**
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	public InputStream getDefaultConfiguration() throws FileNotFoundException {
		return getConfiguration(defaultConfiguration);
	}

	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 */
	public InputStream getConfiguration(String path) throws FileNotFoundException {
		InputStream out = null;
		if (path != null) {
			if (path.startsWith("rtfm:")) {
				String relPath = path.substring(5);
				logger.debug("Relative path: " + relPath);
				out = FileUtils.getResourceAsStream(relPath);
			} else {
				out = new FileInputStream(new File(path));
			}
		}
		logger.debug("Configuration: " + path + ", found: " + out);
		return out;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getConfigurationsList() {
		return Arrays.asList(defaultConfiguration, "rtfm:core/seb.xml", "rtfm:core/default.xml");
	}
}
