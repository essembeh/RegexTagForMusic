package org.essembeh.rtfm.cli.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.cli.config.Configuration;

import com.google.gson.Gson;

public class ConfigurationLoader {

	private static final String RTFM_CONFIGURATION = "RTFM_CONFIG";

	public static Configuration getConfiguration(String customConfiguration) throws IOException {
		File out;
		if (StringUtils.isNotBlank(System.getenv(RTFM_CONFIGURATION))) {
			out = Paths.get(System.getenv(RTFM_CONFIGURATION)).toFile();
		} else if (StringUtils.isNotBlank(customConfiguration)) {
			out = Paths.get(customConfiguration).toFile();
		} else {
			out = Paths.get(System.getProperty("user.home"), ".config", "rtfm.json").toFile();
		}
		return loadConfiguration(out);
	}

	private static Configuration loadConfiguration(File in) throws IOException {
		if (!in.isFile()) {
			throw new FileNotFoundException("Cannot find configuration file: " + in.getAbsolutePath());
		}
		try (FileReader reader = new FileReader(in)) {
			Configuration out = new Gson().fromJson(reader, Configuration.class);
			return out;
		}
	}
}
