package org.essembeh.rtfm.app;

import org.essembeh.rtfm.app.config.RtfmProperties;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ApplicationModule extends AbstractModule {

	public ApplicationModule() {
		super();
	}

	@Override
	protected void configure() {
		bind(RtfmProperties.class).in(Singleton.class);
	}
}
