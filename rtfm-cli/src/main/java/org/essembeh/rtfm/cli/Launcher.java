package org.essembeh.rtfm.cli;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.cli.ParseException;
import org.apache.commons.io.IOUtils;
import org.essembeh.rtfm.cli.app.App;
import org.essembeh.rtfm.cli.app.AppOptions;
import org.essembeh.rtfm.cli.app.callback.MultiCallback;
import org.essembeh.rtfm.cli.config.Configuration;
import org.essembeh.rtfm.cli.report.ConsoleReport;
import org.essembeh.rtfm.cli.report.VerboseConsoleReport;
import org.essembeh.rtfm.cli.utils.ConfigurationLoader;
import org.essembeh.rtfm.cli.utils.ScriptWriter;

public class Launcher {

	public static void main(String[] args) throws IOException, ParseException {
		AppOptions options = AppOptions.parse(args);
		if (!options.displayHelp()) {
			@SuppressWarnings("resource")
			ScriptWriter scriptWriter = null;
			try {
				Configuration configuration = ConfigurationLoader.getConfiguration(options.getCustomConfiguration());
				App app = new App(configuration);

				MultiCallback callback = new MultiCallback();
				if (options.verbose()) {
					callback.add(new VerboseConsoleReport());
				} else {
					callback.add(new ConsoleReport());
				}
				Optional<Path> script = options.getScript();
				if (script.isPresent()) {
					callback.add(scriptWriter = new ScriptWriter(script.get()));
				}

				Optional<Path> database = options.getDatabase();
				if (database.isPresent() && Files.isRegularFile(database.get())) {
					app.loadDatabase(database.get());
				}

				app.setDryRun(options.dryrun());
				app.setResolveEnv(options.useEnv());
				app.setExecuteAllWorkflows(options.executeAllWorkflows());

				for (String arg : options.getArgs()) {
					app.process(Files.walk(Paths.get(arg), FileVisitOption.FOLLOW_LINKS).filter(options::filterFolders), p -> callback);
				}
				if (database.isPresent() && !options.dryrun()) {
					app.saveDatabase(database.get());
				}
			} finally {
				IOUtils.closeQuietly(scriptWriter);
			}
		}
	}
}
