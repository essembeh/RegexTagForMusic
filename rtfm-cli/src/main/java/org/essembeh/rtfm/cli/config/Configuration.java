package org.essembeh.rtfm.cli.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Configuration {

	private final Map<String, List<String>> commands = new HashMap<>();
	private final Map<String, Handler> types = new HashMap<>();

	public Map<String, List<String>> getCommands() {
		return commands;
	}

	public Map<String, Handler> getTypes() {
		return types;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public void check() {
		for (Entry<String, Handler> e : getTypes().entrySet()) {
			List<String> unknownCommands = e.getValue().getWorkflow().stream().filter(commandId -> !getCommands().containsKey(commandId)).collect(Collectors.toList());
			if (!unknownCommands.isEmpty()) {
				throw new IllegalStateException("Type " + e.getKey() + " use unknown commands: " + unknownCommands);
			}
		}
	}
}
