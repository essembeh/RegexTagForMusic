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

import java.io.File;

import org.essembeh.rtfm.core.MusicFile;
import org.essembeh.rtfm.core.conf.Configuration;
import org.essembeh.rtfm.interfaces.IMusicFile;
import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testProperties() throws Throwable {
		Configuration conf = Configuration.instance();
		assertNotNull(conf);
		String appName = conf.getProperty("app.version");
		assertNotNull(appName);
		String plop = conf.getProperty("plop");
		assertNull(plop);
	}

	@Test
	public void testHandlers() throws Exception {
		Configuration configuration = Configuration.instance();
		assertNotNull(configuration);
		File rootFolder = new File("/foo");
		File testFile = new File("/foo/A/B/01 - C.mp3");
		IMusicFile mf = new MusicFile(testFile, rootFolder);
		assertNotNull(mf);
		assertTrue(mf.isTaggable());

	}
}
