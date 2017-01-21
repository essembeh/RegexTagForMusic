package org.essembeh.rtfm.cli;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.cli.ParseException;
import org.essembeh.rtfm.cli.app.App;
import org.essembeh.rtfm.cli.app.AppOptions;
import org.essembeh.rtfm.cli.app.callback.ICallback;
import org.essembeh.rtfm.cli.config.Configuration;
import org.essembeh.rtfm.cli.report.ConsoleReport;
import org.essembeh.rtfm.cli.report.VerboseConsoleReport;
import org.essembeh.rtfm.cli.utils.ConfigurationLoader;

public class Launcher {

	public static void main(String[] args) throws IOException, ParseException {
		AppOptions options = AppOptions.parse(args);
		if (!options.displayHelp()) {
			Configuration configuration = ConfigurationLoader.getConfiguration(options.getCustomConfiguration());
			configuration.check();
			ICallback consoleReport = options.verbose() ? new VerboseConsoleReport() : new ConsoleReport();
			App app = new App(configuration);
			Optional<Path> database = options.getDatabase();
			if (database.isPresent() && Files.isRegularFile(database.get())) {
				app.loadDatabase(database.get());
			}
			app.setDryRun(options.dryrun());
			app.setResolveEnv(options.useEnv());
			app.setExecuteOnlyFirstWorkflow(options.executeOnlyFirstWorkflow());
			for (String arg : options.getArgs()) {
				app.process(Files.walk(Paths.get(arg), FileVisitOption.FOLLOW_LINKS).filter(options::filterFolders), p -> consoleReport);
			}
			if (database.isPresent()) {
				app.saveDatabase(database.get());
			}
		}
	}
}
