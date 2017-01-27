package org.essembeh.rtfm.cli.app;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author seb
 *
 */
public class AppOptions {
	private static final int DEFAULT_THREADS = 1;

	public static final String HELP = "h";
	public static final String VERBOSE = "v";
	public static final String ENV = "e";
	public static final String DRYRUN = "n";
	public static final String CONFIG = "c";
	public static final String FOLDERS = "f";
	public static final String DATABASE = "d";
	public static final String ALL_WORKFLOWS = "a";
	public static final String SCRIPT = "s";
	public static final String THREADS = "j";

	private static final Options OPTIONS = new Options();
	static {
		OPTIONS.addOption(HELP, "help", false, "Display help");
		OPTIONS.addOption(VERBOSE, "verbose", false, "Display more information");
		OPTIONS.addOption(ENV, "env", false, "Use env to resolve variables");
		OPTIONS.addOption(DRYRUN, "dry-run", false, "Dry-run mode, do not execute commands");
		OPTIONS.addOption(CONFIG, "config", true, "Custom configuration file");
		OPTIONS.addOption(FOLDERS, "folders", false, "Also process folders (NB: folders path will end with " + File.separator + ")");
		OPTIONS.addOption(DATABASE, "database", true, "Use database");
		OPTIONS.addOption(ALL_WORKFLOWS, "all", false, "Execute all matching workflows");
		OPTIONS.addOption(SCRIPT, "script", true, "Generate script");
		OPTIONS.addOption(THREADS, "threads", true, "use n threads");
	}

	public static AppOptions parse(String... args) throws ParseException {
		CommandLineParser parser = new DefaultParser();
		return new AppOptions(parser.parse(OPTIONS, args));
	}

	private final CommandLine commandLine;

	public AppOptions(CommandLine commandLine) {
		this.commandLine = commandLine;
	}

	protected Optional<String> getOptionValue(String option) {
		if (commandLine.hasOption(option)) {
			return Optional.of(commandLine.getOptionValue(option));
		}
		return Optional.empty();
	}

	public boolean canProcess() {
		return !commandLine.hasOption(HELP);
	}

	public void displayHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(100);
		formatter.printHelp("rtfm", OPTIONS);
	}

	public boolean verbose() {
		return commandLine.hasOption(VERBOSE);
	}

	public boolean dryrun() {
		return commandLine.hasOption(DRYRUN);
	}

	public boolean useEnv() {
		return commandLine.hasOption(ENV);
	}

	public List<String> getArgs() {
		return commandLine.getArgList();
	}

	public Optional<Path> getCustomConfiguration() {
		return getOptionValue(CONFIG).map(Paths::get);
	}

	public Optional<Path> getDatabase() {
		return getOptionValue(DATABASE).map(Paths::get);
	}

	public Optional<Path> getScript() {
		return getOptionValue(SCRIPT).map(Paths::get);
	}

	public boolean processFolders() {
		return commandLine.hasOption(FOLDERS);
	}

	public boolean filterFolders(Path in) {
		return processFolders() || !Files.isDirectory(in);
	}

	public boolean executeAllWorkflows() {
		return commandLine.hasOption(ALL_WORKFLOWS);
	}

	public int getThreads() {
		return getOptionValue(THREADS).map(Integer::parseInt).orElse(DEFAULT_THREADS);
	}
}
