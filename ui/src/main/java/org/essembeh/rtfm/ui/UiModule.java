package org.essembeh.rtfm.ui;

import org.essembeh.rtfm.core.ApplicationModule;

import com.google.inject.name.Names;

public class UiModule extends ApplicationModule {

	private final String customConfigurationFileArg;

	public UiModule(String[] args) {
		super();
		customConfigurationFileArg = args.length > 0 ? args[0] : "";
	}

	@Override
	protected void configure() {
		super.configure();
		bind(String.class).annotatedWith(Names.named("customConfigurationFile")).toInstance(customConfigurationFileArg);
	}
}
