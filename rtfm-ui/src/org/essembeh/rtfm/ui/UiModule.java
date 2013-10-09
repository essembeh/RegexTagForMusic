package org.essembeh.rtfm.ui;
import org.essembeh.rtfm.app.config.RtfmProperties;

import com.google.inject.AbstractModule;

public class UiModule extends AbstractModule {

	private final RtfmProperties mainProperties;

	public UiModule(RtfmProperties mainProperties) {
		super();
		this.mainProperties = mainProperties;
	}

	@Override
	protected void configure() {
		bind(RtfmProperties.class).toInstance(mainProperties);
	}

}
