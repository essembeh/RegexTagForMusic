package org.essembeh.rtfm.core.configuration;

import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.essembeh.rtfm.core.configuration.internal.EmptyConfigurationTemplate;
import org.essembeh.rtfm.core.configuration.internal.SebConfigurationTemplate;
import org.essembeh.rtfm.model.rtfm.Configuration;

public class ConfigurationManager {

	private static final String APP_SCHEME = "rtfm";
	public static final URI DEFAULT_URI = URI.create(APP_SCHEME + ":" + EmptyConfigurationTemplate.ID);

	public static ConfigurationManager INSTANCE = new ConfigurationManager();

	private final Map<String, IConfigurationTemplate> templates = new TreeMap<>();

	private ConfigurationManager() {
		add(new EmptyConfigurationTemplate());
		add(new SebConfigurationTemplate());
	}

	protected void add(IConfigurationTemplate configurationGenerator) {
		templates.put(configurationGenerator.getId(), configurationGenerator);
	}

	public Set<URI> getAvailableTemplates() {
		return templates.keySet().stream().map(s -> URI.create(APP_SCHEME + ":" + s)).collect(Collectors.toSet());
	}

	public Configuration getDefault() {
		return load(DEFAULT_URI);
	}

	public Configuration load(URI uri) {
		if (APP_SCHEME.equals(uri.getScheme())) {
			return templates.get(uri.getSchemeSpecificPart()).createModel();
		}
		throw new IllegalArgumentException("Cannot retrieve configuration from " + uri);
	}
}
