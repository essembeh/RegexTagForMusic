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

package org.essembeh.rtfm.junit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;

import org.essembeh.rtfm.core.MusicFile;
import org.essembeh.rtfm.core.conf.Configuration;
import org.essembeh.rtfm.core.conf.RTFMProperties;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.interfaces.IMusicFile;
import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testConfiguration() throws Exception {
		assertNotNull(Configuration.init());
	}

	@Test
	public void testProperties() throws Throwable {
		assertNotNull(RTFMProperties.getProperty("app.version"));
		assertNull(RTFMProperties.getProperty("plop"));
		try {
			RTFMProperties.getMandatoryProperty("plop");
			fail();
		} catch (ConfigurationException e) {
			// OK
		}
	}

	@Test
	public void testHandlers() throws Exception {
		assertNotNull(Configuration.init());
		File rootFolder = new File("/foo");
		File testFile = new File("/foo/A/B/01 - C.mp3");
		IMusicFile mf = new MusicFile(testFile, rootFolder);
		assertNotNull(mf);
		assertTrue(mf.isTaggable());

	}
}
