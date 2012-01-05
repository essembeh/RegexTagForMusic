package org.essembeh.rtfm.core;

import org.essembeh.rtfm.core.configuration.io.CoreConfigurationLoader;
import org.essembeh.rtfm.core.configuration.io.CoreConfigurationLoaderV1;
import org.essembeh.rtfm.core.event.EventHandler;
import org.essembeh.rtfm.core.event.LoggerEventHandler;
import org.essembeh.rtfm.core.library.io.GenericLibraryLoader;
import org.essembeh.rtfm.core.library.io.LibraryLoader;
import org.essembeh.rtfm.core.library.io.LibraryWriter;
import org.essembeh.rtfm.core.library.io.LibraryWriterV2;
import org.essembeh.rtfm.core.properties.RTFMProperties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class CoreModule extends AbstractModule {

	RTFMProperties mainProperties;

	public CoreModule(RTFMProperties mainProperties) {
		this.mainProperties = mainProperties;
	}

	@Override
	protected void configure() {
		bind(EventHandler.class).toInstance(new LoggerEventHandler());
		bind(LibraryWriter.class).to(LibraryWriterV2.class);
		bind(LibraryLoader.class).to(GenericLibraryLoader.class);
		bind(CoreConfigurationLoader.class).to(CoreConfigurationLoaderV1.class);
		bind(RTFMProperties.class).annotatedWith(Names.named("rtfm.properties")).toInstance(mainProperties);
	}
}
