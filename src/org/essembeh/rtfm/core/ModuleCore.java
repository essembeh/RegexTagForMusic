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

import org.essembeh.rtfm.core.configuration.CoreConfiguration;
import org.essembeh.rtfm.core.configuration.CoreConfigurationServices;
import org.essembeh.rtfm.core.configuration.IWorkflowService;
import org.essembeh.rtfm.core.configuration.IXFileService;
import org.essembeh.rtfm.core.configuration.io.MultiCoreConfigurationReader;
import org.essembeh.rtfm.core.library.Library;
import org.essembeh.rtfm.core.library.io.LibraryWriterV2;
import org.essembeh.rtfm.core.library.io.MultiLibraryReader;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.version.IObjectReader;
import org.essembeh.rtfm.core.utils.version.IObjectWriter;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public class ModuleCore extends AbstractModule {

	/**
	 * Attributes
	 */
	private final RTFMProperties mainProperties;

	/**
	 * 
	 * @param mainProperties
	 */
	public ModuleCore(RTFMProperties mainProperties) {
		this.mainProperties = mainProperties;
	}

	@Override
	protected void configure() {
		try {
			// Singleton for properties
			bind(RTFMProperties.class).toInstance(mainProperties);

			// Singleton for the configuration and services
			bind(CoreConfigurationServices.class).in(Singleton.class);
			bind(IWorkflowService.class).to(CoreConfigurationServices.class).in(Singleton.class);
			bind(IXFileService.class).to(CoreConfigurationServices.class).in(Singleton.class);
			
			// IO
			bind(new TypeLiteral<IObjectReader<Library>>() {
			}).annotatedWith(Names.named("LibraryReader")).to(MultiLibraryReader.class);

			bind(new TypeLiteral<IObjectWriter<Library>>() {
			}).annotatedWith(Names.named("LibraryWriter")).to(LibraryWriterV2.class);

			bind(new TypeLiteral<IObjectReader<CoreConfiguration>>() {
			}).annotatedWith(Names.named("CoreConfigurationReader")).to(MultiCoreConfigurationReader.class);

		} catch (Exception e) {
			addError(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(((String) null).getClass());
	}
}
