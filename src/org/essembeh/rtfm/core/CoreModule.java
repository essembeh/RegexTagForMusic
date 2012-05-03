/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core;

import org.essembeh.rtfm.core.configuration.io.CoreConfigurationLoaderV1;
import org.essembeh.rtfm.core.configuration.io.ICoreConfigurationLoader;
import org.essembeh.rtfm.core.library.io.ILibraryLoader;
import org.essembeh.rtfm.core.library.io.ILibraryWriter;
import org.essembeh.rtfm.core.library.io.LibraryLoaderV1;
import org.essembeh.rtfm.core.library.io.LibraryLoaderV2;
import org.essembeh.rtfm.core.library.io.LibraryWriterV2;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.FileUtils;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class CoreModule extends AbstractModule {

	RTFMProperties mainProperties;

	public CoreModule(RTFMProperties mainProperties) {
		this.mainProperties = mainProperties;
	}

	@Override
	protected void configure() {
		try {
			// Singleton
			bind(RTFMProperties.class).toInstance(mainProperties);

			// Load configuration
			bind(ICoreConfigurationLoader.class).toInstance(
					new CoreConfigurationLoaderV1(FileUtils.getResourceAsStream(mainProperties
							.getMandatoryProperty("configuration.core"))));

			// Interfaces
			bind(ILibraryLoader.class).annotatedWith(Names.named("LibraryLoaderV1")).to(LibraryLoaderV1.class);
			bind(ILibraryLoader.class).annotatedWith(Names.named("LibraryLoaderV2")).to(LibraryLoaderV2.class);
			bind(ILibraryWriter.class).to(LibraryWriterV2.class);
		} catch (Exception e) {
			addError(e);
		}
	}
}
