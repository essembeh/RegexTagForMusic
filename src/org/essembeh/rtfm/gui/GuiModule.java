package org.essembeh.rtfm.gui;

import org.essembeh.rtfm.core.properties.RTFMProperties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class GuiModule extends AbstractModule {

	RTFMProperties mainProperties;

	public GuiModule(RTFMProperties mainProperties) {
		this.mainProperties = mainProperties;
	}

	@Override
	protected void configure() {
		bind(RTFMProperties.class).annotatedWith(Names.named("rtfm.properties")).toInstance(mainProperties);
	}
}
