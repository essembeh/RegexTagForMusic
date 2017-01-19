package org.essembeh.rtfm.cli.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.cli.config.Configuration;

import com.google.gson.Gson;

public class ConfigurationLoader {

	private static final String RTFM_CONFIGURATION = "RTFM_CONFIG";

	public static Configuration getConfiguration(Optional<Path> customConfig) throws IOException {
		Path config;
		if (customConfig.isPresent()) {
			config = customConfig.get();
		} else if (StringUtils.isNotBlank(System.getenv(RTFM_CONFIGURATION))) {
			config = Paths.get(System.getenv(RTFM_CONFIGURATION));
		} else {
			config = Paths.get(System.getProperty("user.home"), ".config", "rtfm.json");
		}
		return loadConfiguration(config);
	}

	public static Configuration loadConfiguration(Path config) throws IOException {
		try (BufferedReader reader = Files.newBufferedReader(config)) {
			Configuration out = new Gson().fromJson(reader, Configuration.class);
			return out;
		}
	}
}
