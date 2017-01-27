package org.essembeh.rtfm.cli;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.ParseException;
import org.essembeh.rtfm.cli.app.App;
import org.essembeh.rtfm.cli.app.AppOptions;
import org.essembeh.rtfm.cli.app.callback.MultiCallback;
import org.essembeh.rtfm.cli.report.ConsoleReport;
import org.essembeh.rtfm.cli.utils.ConfigurationLoader;
import org.essembeh.rtfm.cli.utils.ScriptWriter;

public class Launcher {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		AppOptions options = AppOptions.parse(args);
		if (options.canProcess()) {
			try (ScriptWriter scriptWriter = new ScriptWriter(options.getScript())) {
				App app = new App(ConfigurationLoader.getConfiguration(options.getCustomConfiguration()));
				ConsoleReport consoleReport = new ConsoleReport(options.verbose(), options.getThreads());
				if (options.getDatabase().map(Files::isRegularFile).orElse(false)) {
					app.loadDatabase(options.getDatabase().get());
				}
				app.setDryRun(options.dryrun());
				app.setResolveEnv(options.useEnv());
				app.setExecuteAllWorkflows(options.executeAllWorkflows());
				ExecutorService executor = Executors.newFixedThreadPool(options.getThreads());
				for (String arg : options.getArgs()) {
					Files.walk(Paths.get(arg), FileVisitOption.FOLLOW_LINKS).filter(options::filterFolders).forEach(file -> {
						executor.execute(app.processLater(file, MultiCallback.with(scriptWriter.createCallback(file), consoleReport.createCallback(file))));
					});
				}
				executor.shutdown();
				executor.awaitTermination(1, TimeUnit.DAYS);
				if (options.getDatabase().isPresent() && !options.dryrun()) {
					app.saveDatabase(options.getDatabase().get());
				}
			}
		} else {
			options.displayHelp();
		}
	}
}
