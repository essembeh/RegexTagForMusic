package org.essembeh.rtfm.cli.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Workflow {

	private String pattern;
	private final List<String> execute = new ArrayList<>();
	private final Map<String, String> variables = new HashMap<>();

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public List<String> getExecute() {
		return execute;
	}

	public Map<String, String> getVariables() {
		return variables;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public Matcher matcher(String absolutePath) {
		return Pattern.compile(pattern).matcher(absolutePath);
	}
}
