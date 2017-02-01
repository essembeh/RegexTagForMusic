package org.essembeh.rtfm.core.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public interface TestConstants {
	String SAMPLES_PREFIX = "../samples/config";

	Path DEFAULT_CONFIG = Paths.get(SAMPLES_PREFIX, "default.json");
	Path DEFAULT_ROOT = Paths.get(SAMPLES_PREFIX, "default");

	Path SEB_CONFIG = Paths.get(SAMPLES_PREFIX, "seb.json");
	Path SEB_ROOT = Paths.get(SAMPLES_PREFIX, "seb");

	Path CLEAN_CONFIG = Paths.get(SAMPLES_PREFIX, "clean.json");
}
