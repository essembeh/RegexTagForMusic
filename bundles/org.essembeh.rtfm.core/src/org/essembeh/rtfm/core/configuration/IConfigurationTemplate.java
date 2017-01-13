package org.essembeh.rtfm.core.configuration;

import org.essembeh.rtfm.model.rtfm.Configuration;

public interface IConfigurationTemplate {

	String getId();

	String getName();

	Configuration createModel();
}
