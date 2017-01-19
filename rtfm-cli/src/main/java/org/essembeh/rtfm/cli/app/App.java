package org.essembeh.rtfm.cli.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Triple;
import org.essembeh.rtfm.cli.callback.ICallback;
import org.essembeh.rtfm.cli.config.Configuration;
import org.essembeh.rtfm.cli.config.Handler;
import org.essembeh.rtfm.cli.resolver.BuiltinVariables;
import org.essembeh.rtfm.cli.resolver.VariableSubstitutor;

public class App {

	private final Configuration configuration;

	boolean resolveEnv = false;
	boolean dryRun = false;

	public App(Configuration configuration) {
		this.configuration = configuration;
	}

	public boolean dryRun() {
		return dryRun;
	}

	public boolean resolveEnv() {
		return resolveEnv;
	}

	public void setDryRun(boolean dryRun) {
		this.dryRun = dryRun;
	}

	public void setResolveEnv(boolean resolveEnv) {
		this.resolveEnv = resolveEnv;
	}

	private Matcher testHandler(Handler handler, String path) {
		Matcher out = Pattern.compile(handler.getPattern()).matcher(path);
		return out.matches() ? out : null;
	}

	private Optional<Triple<String, Handler, Matcher>> findHandler(String path) {
		for (Entry<String, Handler> e : configuration.getTypes().entrySet()) {
			Matcher m = testHandler(e.getValue(), path);
			if (m != null) {
				return Optional.of(Triple.of(e.getKey(), e.getValue(), m));
			}
		}
		return Optional.empty();
	}

	public void process(Stream<Path> stream, Function<Path, ICallback> callbackFactory) {
		stream.forEach(p -> process(p, callbackFactory.apply(p)));
	}

	public void process(Path in, ICallback callback) {
		String fullpath = BuiltinVariables.PATH.resolve(in);
		try {
			Optional<Triple<String, Handler, Matcher>> handler = findHandler(fullpath);
			if (handler.isPresent()) {
				callback.fileHandled(fullpath, handler.get().getLeft());
				executeWorkflow(in, handler.get().getMiddle(), handler.get().getRight(), callback);
			} else {
				callback.unknownType(fullpath);
			}
		} catch (Exception e) {
			callback.workflowException(e);
		} finally {
			callback.done();
		}
	}

	protected void executeWorkflow(Path in, Handler fileHandler, Matcher matcher, ICallback callback) throws IOException, InterruptedException {
		VariableSubstitutor resolver = new VariableSubstitutor(in, matcher, fileHandler.getVariables(), resolveEnv);
		callback.workflowBegins(fileHandler.getWorkflow());
		for (String commandId : fileHandler.getWorkflow()) {
			List<String> rawCommand = configuration.getCommands().get(commandId);
			callback.commandBegins(commandId, rawCommand);
			List<String> resolvedCommand = new ArrayList<>();
			for (String rawString : rawCommand) {
				String resolvedString = resolver.replace(rawString);
				if (!resolver.isComplete(resolvedString)) {
					throw new IllegalStateException("Cannot resolve variable from: " + resolvedString);
				}
				resolvedCommand.add(resolvedString);
			}
			callback.commandResolved(commandId, resolvedCommand);
			ProcessStatus status = dryRun ? ProcessStatus.dryRun(resolvedCommand) : ProcessStatus.execute(resolvedCommand);
			callback.commandEnds(commandId, status);
			if (status.getReturnCode() != 0) {
				break;
			}
		}
	}
}
