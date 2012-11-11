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
package org.essembeh.rtfm.ui;

import org.essembeh.rtfm.core.properties.RTFMProperties;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ModuleUI extends AbstractModule {

	RTFMProperties mainProperties;

	public ModuleUI(RTFMProperties mainProperties) {
		this.mainProperties = mainProperties;
	}

	@Override
	protected void configure() {
		bind(RTFMProperties.class).annotatedWith(Names.named("rtfm.properties")).toInstance(mainProperties);
	}
}
