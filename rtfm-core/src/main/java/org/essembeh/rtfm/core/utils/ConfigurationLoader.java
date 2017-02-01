package org.essembeh.rtfm.core.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ConfigurationLoader {

	private static final String RTFM_CONFIGURATION = "RTFM_CONFIG";

	public static Path getConfigurationFile() {
		return getEnvConfigurationFile().orElseGet(ConfigurationLoader::getHomeConfigConfigurationFile);
	}

	public static Path getHomeConfigConfigurationFile() {
		Path out = Paths.get(System.getProperty("user.home"), ".config", "rtfm.json");
		if (!Files.isRegularFile(out)) {
			throw new IllegalStateException("Cannot find configuration file: " + out.toString());
		}
		return out;
	}

	public static Optional<Path> getEnvConfigurationFile() {
		String value = System.getenv(RTFM_CONFIGURATION);
		if (value == null) {
			return Optional.empty();
		}
		Path out = Paths.get(value);
		if (!Files.isRegularFile(out)) {
			throw new IllegalStateException("Invalid configuration file: " + value);
		}
		return Optional.of(out);
	}
}
