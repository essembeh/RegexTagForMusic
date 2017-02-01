package org.essembeh.rtfm.core.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.essembeh.rtfm.core.utils.JsonUtils;

public class Configuration {

	private final Map<String, List<String>> commands = new HashMap<>();
	private final Map<String, Workflow> workflows = new HashMap<>();

	public Map<String, List<String>> getCommands() {
		return commands;
	}

	public Map<String, Workflow> getWorkflows() {
		return workflows;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public void check() {
		getWorkflows().forEach((id, w) -> {
			List<String> unknownCommands = w.getExecute().stream().filter(commandId -> !getCommands().containsKey(commandId)).collect(Collectors.toList());
			if (!unknownCommands.isEmpty()) {
				throw new IllegalStateException("Workflow " + id + " use unknown commands: " + unknownCommands);
			}
		});
	}

	public static Configuration load(Path in) throws IOException {
		return JsonUtils.loadJson(in, Configuration.class);
	}
}
