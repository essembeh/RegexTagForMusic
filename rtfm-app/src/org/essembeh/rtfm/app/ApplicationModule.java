package org.essembeh.rtfm.app;

import org.essembeh.rtfm.app.config.RtfmProperties;

import com.google.inject.AbstractModule;

public class ApplicationModule extends AbstractModule {
	private final RtfmProperties mainProperties;

	public ApplicationModule(RtfmProperties mainProperties) {
		super();
		this.mainProperties = mainProperties;
	}

	@Override
	protected void configure() {
		bind(RtfmProperties.class).toInstance(mainProperties);
	}
}
