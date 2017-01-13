package org.essembeh.rtfm.cli;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.essembeh.rtfm.cli.app.App;
import org.essembeh.rtfm.cli.app.Options;

import com.beust.jcommander.JCommander;

public class Launcher {

	public static void main(String[] args) throws IOException {
		Options options = new Options();
		JCommander commander = new JCommander(options);
		commander.setProgramName("rtfm-cli");
		commander.parse(args);
		if (options.displayUsage()) {
			commander.usage();
		} else {
			App app = new App(options.getConfiguration(), options.getLogger());
			app.setDryRun(options.dryrun());
			app.setResolveEnv(options.useEnv());
			for (Path arg : options.getFiles()) {
				Files.walkFileTree(arg, new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						app.process(file);
						return super.visitFile(file, attrs);
					}
				});
			}
		}
	}
}
