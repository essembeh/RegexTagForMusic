package org.essembeh.rtfm.cli;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.ParseException;
import org.essembeh.rtfm.core.app.App;
import org.essembeh.rtfm.core.app.callback.ICallback;
import org.essembeh.rtfm.core.app.callback.MultiCallback;
import org.essembeh.rtfm.core.config.Configuration;
import org.essembeh.rtfm.core.report.ConsoleReport;
import org.essembeh.rtfm.core.utils.ConfigurationLoader;
import org.essembeh.rtfm.core.utils.ResourceUtils;
import org.essembeh.rtfm.core.utils.ScriptWriter;

public class Launcher {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		AppOptions options = AppOptions.parse(args);
		if (options.canProcess()) {
			try (ScriptWriter scriptWriter = new ScriptWriter(options.getScript())) {
				Path configuration = options.getCustomConfiguration().orElseGet(ConfigurationLoader::getConfigurationFile);
				App app = new App(Configuration.load(configuration));
				ConsoleReport consoleReport = new ConsoleReport(options.verbose(), options.getThreads());
				if (options.getDatabase().map(Files::isRegularFile).orElse(false)) {
					app.loadDatabase(options.getDatabase().get());
				}
				app.setDryRun(options.dryrun());
				app.setResolveEnv(options.useEnv());
				app.setExecuteAllWorkflows(options.executeAllWorkflows());
				ExecutorService executor = Executors.newFixedThreadPool(options.getThreads());
				for (String arg : options.getArgs()) {
					//@formatter:off 
					Files.walk(Paths.get(arg), FileVisitOption.FOLLOW_LINKS)
						.filter(options::filterFolders)
						.sorted(Comparator.comparing(ResourceUtils::getFullPath))
						.forEach(file -> {
							ICallback callback = MultiCallback.with(scriptWriter.createCallback(file), consoleReport.createCallback(file));
							executor.execute(app.processLater(file, callback));
						});
					//@formatter:on
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
