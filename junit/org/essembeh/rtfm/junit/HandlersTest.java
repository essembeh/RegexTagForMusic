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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.essembeh.rtfm.core.FileHandler;
import org.essembeh.rtfm.core.MusicFile;
import org.essembeh.rtfm.core.conf.Configuration;
import org.junit.Before;
import org.junit.Test;

public class HandlersTest {

	static Configuration conf;

	@Before
	public void testConfiguration() throws Throwable {
		conf = Configuration.instance();
		assertNotNull(conf);
	}

	@Test
	public void testCoverChecker() {
		assertNotNull(conf);
		File root = new File("/m/");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("/m/a/b/cover.jpg", true);
		map.put("/m/a/2000 - b/cover.jpg", true);
		map.put("/m/a/b/cover.jpeg", true);
		map.put("/m/a/b/cover.png", true);
		map.put("/m/a/b/c/cover.jpg", false);
		map.put("/m/a/b/cover2.jpg", false);
		map.put("/m/a/cover.jpg", false);
		for (String path : map.keySet()) {
			File file = new File(path);
			Boolean result = map.get(path);
			FileHandler handler = conf.getHandlerForFile(file);
			assertNotNull(handler);
			MusicFile musicFile = new MusicFile(file, root, handler);
			assertNotNull(musicFile);
			assertEquals(musicFile.getType(), "COVER");
			assertEquals(musicFile.isValid(), result.booleanValue());
		}
	}

	@Test
	public void testMp3Checker() {
		assertNotNull(conf);
		File root = new File("/m/");
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("/m/a/b/01 - c.mp3", true);
		map.put("/m/a/2000 - b/01 - c.mp3", true);
		map.put("/m/a/b/01. c.mp3", false);
		map.put("/m/Group/1978 - album/01 - Title (feat. plop) [2] ! & #.mp3",
				true);
		for (String path : map.keySet()) {
			File file = new File(path);
			Boolean result = map.get(path);
			FileHandler handler = conf.getHandlerForFile(file);
			assertNotNull(handler);
			MusicFile musicFile = new MusicFile(file, root, handler);
			assertNotNull(musicFile);
			assertEquals(musicFile.getType(), "MP3");
			assertEquals(musicFile.isValid(), result.booleanValue());
		}
		System.out.println("");
	}
}
