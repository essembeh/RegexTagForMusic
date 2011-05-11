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

import java.util.HashMap;
import java.util.Map;

import org.essembeh.rtfm.core.FileHandler;
import org.essembeh.rtfm.core.conf.Configuration;
import org.essembeh.rtfm.core.conf.Services;
import org.junit.Before;
import org.junit.Test;

public class HandlersTest {

	@Before
	public void testConfiguration() throws Throwable {
		assertNotNull(Configuration.init());
	}

	@Test
	public void testCover() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("/a/b/cover.jpg", "COVER");
		map.put("/a/2000 - b/cover.jpg", "COVER");
		map.put("/a/b/cover.jpeg", "COVER");
		map.put("/a/b/cover.png", "COVER");
		map.put("/a/b/c/cover.jpg", "UNKNOWN");
		map.put("/a/b/cover2.jpg", "UNKNOWN");
		map.put("/a/cover.jpg", "UNKNOWN");
		for (String path : map.keySet()) {
			String result = map.get(path);
			FileHandler handler = Services.instance().getFileHandlerForFile(path);
			assertNotNull(handler);
			assertEquals(handler.getId(), result);
		}
	}

	@Test
	public void testMp3() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("/a/b/01 - c.mp3", "MP3");
		map.put("/a/2000 - b/01 - c.mp3", "MP3");
		map.put("/a/b/01. c.mp3", "UNKNOWN");
		map.put("/Group/1978 - album/01 - Title (feat. plop) [2] ! & #.mp3", "MP3");
		for (String path : map.keySet()) {
			String result = map.get(path);
			FileHandler handler = Services.instance().getFileHandlerForFile(path);
			assertNotNull(handler);
			assertEquals(handler.getId(), result);
		}
		System.out.println("");
	}
}
