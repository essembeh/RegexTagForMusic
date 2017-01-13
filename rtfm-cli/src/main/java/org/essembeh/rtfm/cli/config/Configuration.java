package org.essembeh.rtfm.cli.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
