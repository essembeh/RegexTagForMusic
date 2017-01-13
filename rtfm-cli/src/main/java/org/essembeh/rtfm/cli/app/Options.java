package org.essembeh.rtfm.cli.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.cli.config.Configuration;

import com.beust.jcommander.Parameter;
import com.google.gson.Gson;

public class Options {

	private static final String RTFM_CONFIGURATION = "RTFM_CONFIG";

	@Parameter(names = { "--help", "-h" }, help = true, description = "Display usage")
	private boolean help;

	@Parameter(names = { "--verbose", "-v" }, description = "Verbose mode")
	private boolean verbose = false;

	@Parameter(names = { "--env", "-e" }, description = "Use env to resolve variables")
	private boolean env = false;

	@Parameter(names = { "--dry-run", "-n" }, description = "Dry-run, do not execute commands")
	private boolean dryrun = false;

	@Parameter(names = { "--quiet", "-q" }, description = "Quiet mode")
	private boolean quiet = false;

	@Parameter(names = { "--config", "-c" }, description = "Custom configuration file")
	private String customConfigurationFile = null;

	@Parameter(description = "Files")
	private List<String> files = new ArrayList<>();

	public Logger getLogger() {
		int mask = Logger.INFO | Logger.ERROR;
		if (verbose) {
			mask = Logger.DEBUG | mask;
		} else if (quiet) {
			mask = Logger.ERROR;
		}
		return new Logger(mask);
	}

	public Configuration getConfiguration() throws IOException {
		File out;
		if (StringUtils.isNotBlank(System.getenv(RTFM_CONFIGURATION))) {
			out = Paths.get(System.getenv(RTFM_CONFIGURATION)).toFile();
		} else if (StringUtils.isNotBlank(customConfigurationFile)) {
			out = Paths.get(customConfigurationFile).toFile();
		} else {
			out = Paths.get(System.getProperty("user.home"), ".config", "rtfm.json").toFile();
		}
		return loadConfiguration(out);
	}

	private Configuration loadConfiguration(File in) throws IOException {
		if (!in.isFile()) {
			throw new FileNotFoundException("Cannot find configuration file: " + in.getAbsolutePath());
		}
		try (FileReader reader = new FileReader(in)) {
			Configuration out = new Gson().fromJson(reader, Configuration.class);
			return out;
		}
	}

	public List<Path> getFiles() throws IOException {
		List<Path> out = new ArrayList<>();
		for (String string : files) {
			Path p = Paths.get(string).toRealPath();
			if (Files.exists(p)) {
				out.add(p);
			}
		}
		return out;
	}

	public boolean displayUsage() {
		return help;
	}

	public boolean dryrun() {
		return dryrun;
	}

	public boolean useEnv() {
		return env;
	}
}
