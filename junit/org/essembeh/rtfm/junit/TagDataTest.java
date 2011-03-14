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

import java.io.File;


import org.essembeh.rtfm.core.FileHandler;
import org.essembeh.rtfm.core.MusicFile;
import org.essembeh.rtfm.core.conf.Configuration;
import org.essembeh.rtfm.core.tag.TagData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TagDataTest {

	static Configuration conf;

	@Before
	public void testConf() throws Exception {
		conf = Configuration.instance();
		assertNotNull(conf);
	}

	@Test
	public void testWithInvalidYear() throws Exception {
		assertNotNull(conf);
		File root = new File("/m/");
		File mp3 = new File("/m/the artist/3000 - the album/01 - the song.mp3");
		FileHandler handler = conf.getHandlerForFile(mp3);
		assertNotNull(handler);
		MusicFile musicFile = new MusicFile(mp3, root, handler);
		TagData tag = musicFile.getTagData();
		assertNotNull(tag);
		assertEquals("the artist", tag.getArtist());
		assertEquals("3000 - the album", tag.getAlbum());
		assertNull(tag.getYear());
		assertEquals("the song", tag.getTrackName());
		assertEquals("01", tag.getTrackNumber());
		assertEquals("Tagged with RegexTagForMusic", tag.getComment());
	}

	@Test
	public void testWithoutYear() throws Exception {
		assertNotNull(conf);
		File root = new File("/m/");
		File mp3 = new File("/m/the artist/the album/01 - the song.mp3");
		FileHandler handler = conf.getHandlerForFile(mp3);
		assertNotNull(handler);
		MusicFile musicFile = new MusicFile(mp3, root, handler);
		TagData tag = musicFile.getTagData();
		assertNotNull(tag);
		assertEquals("the artist", tag.getArtist());
		assertEquals("the album", tag.getAlbum());
		assertNull(tag.getYear());
		assertEquals("the song", tag.getTrackName());
		assertEquals("01", tag.getTrackNumber());
		assertEquals("Tagged with RegexTagForMusic", tag.getComment());
	}

	@Test
	public void testWithYear() throws Exception {
		assertNotNull(conf);
		File root = new File("/m/");
		File mp3 = new File("/m/the artist/2000 - the album/01 - the song.mp3");
		FileHandler handler = conf.getHandlerForFile(mp3);
		assertNotNull(handler);
		MusicFile musicFile = new MusicFile(mp3, root, handler);
		TagData tag = musicFile.getTagData();
		assertNotNull(tag);
		assertEquals("the artist", tag.getArtist());
		assertEquals("2000 - the album", tag.getAlbum());
		assertEquals("2000", tag.getYear());
		assertEquals("the song", tag.getTrackName());
		assertEquals("01", tag.getTrackNumber());
		assertEquals("Tagged with RegexTagForMusic", tag.getComment());
	}
}
