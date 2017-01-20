package org.essembeh.rtfm.cli.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface Constants {
	String SAMPLES_PREFIX = "../samples/config";

	Path DEFAULT_CONFIG = Paths.get(SAMPLES_PREFIX, "default.json");
	Path DEFAULT_ROOT = Paths.get(SAMPLES_PREFIX, "default");

	Path SEB_CONFIG = Paths.get(SAMPLES_PREFIX, "seb.json");
	Path SEB_ROOT = Paths.get(SAMPLES_PREFIX, "seb");

	Path CLEAN_CONFIG = Paths.get(SAMPLES_PREFIX, "clean.json");
}
