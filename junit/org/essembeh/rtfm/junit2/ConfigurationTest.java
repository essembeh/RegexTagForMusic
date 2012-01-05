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

package org.essembeh.rtfm.junit2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.essembeh.rtfm.core.configuration.MusicFileCreatorImpl;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.interfaces.MusicFile;
import org.essembeh.rtfm.core.library.file.MusicFileImpl;
import org.essembeh.rtfm.core.properties.RTFMPropertiesFromFile;
import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testConfiguration() throws Exception {
		assertNotNull(MusicFileCreatorImpl.init());
	}

	@Test
	public void testProperties() throws Throwable {
		assertNotNull(RTFMPropertiesFromFile.getProperty("app.version"));
		assertNull(RTFMPropertiesFromFile.getProperty("plop"));
		try {
			RTFMPropertiesFromFile.getMandatoryProperty("plop");
			fail();
		} catch (ConfigurationException e) {
			// OK
		}
	}

	@Test
	public void testHandlers() throws Exception {
		assertNotNull(MusicFileCreatorImpl.init());
		File rootFolder = new File("/foo");
		File testFile = new File("/foo/A/B/01 - C.mp3");
		MusicFile mf = new MusicFileImpl(testFile, rootFolder);
		assertNotNull(mf);
		assertTrue(mf.isTaggable());

	}
}
