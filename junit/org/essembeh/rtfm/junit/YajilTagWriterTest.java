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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.ITagWriter;
import org.essembeh.rtfm.junit.utils.BinaryUtils;
import org.essembeh.rtfm.plugins.YajilTagWriter;
import org.junit.Before;
import org.junit.Test;

public class YajilTagWriterTest {

	private static final int HEADER_SIZE = 2048;

	ITagWriter tagger;

	File mp3 = new File("test/default/dummy.mp3");

	@Before
	public void init() {
		this.tagger = new YajilTagWriter();
		assertTrue(this.mp3.exists());
		assertTrue(this.mp3.isFile());
	}

	@Test
	public void testRemoveTags() throws Throwable {
		TagData tag = new TagData("SuperArtist", "2000", "bb", "01", "cc", "dd", null);
		assertTrue(this.tagger.tag(this.mp3, tag, false));
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes(), HEADER_SIZE));
		this.tagger.removeTag(this.mp3, true);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes(), HEADER_SIZE));
		this.tagger.removeTag(this.mp3, false);
		assertFalse(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes(), HEADER_SIZE));
	}

}
