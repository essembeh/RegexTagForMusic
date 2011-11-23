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

import junit.framework.Assert;

import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.interfaces.ITagWriter;
import org.essembeh.rtfm.junit.utils.BinaryUtils;
import org.essembeh.rtfm.plugins.EyeD3TagWriter;
import org.junit.Before;
import org.junit.Test;

public class EyeD3TaggerTest {

	private static final int HEADER_SIZE = 2048;

	ITagWriter tagger;

	File mp3 = new File("test/default/dummy.mp3");

	@Before
	public void init() {
		this.tagger = new EyeD3TagWriter();
		this.tagger.setProperty("eyed3.binary", "/usr/bin/eyeD3");
		this.tagger.setProperty("eyed3.default.arguments", "--no-color");
		this.tagger.setProperty("force.utf8", "true");
		assertTrue(this.mp3.exists());
		assertTrue(this.mp3.isFile());
	}

	@Test
	public void testTag1() throws Throwable {
		TagData tag = new TagData("aa", "2000", "bb", "01", "cc", "dd", null);
		Assert.assertTrue(this.mp3.isFile());
		this.tagger.tag(this.mp3, tag);
		Assert.assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes(), HEADER_SIZE));
	}

	@Test
	public void testTag2() throws Throwable {
		TagData tag = new TagData("The Artist", "2000", "The Album", "01", "Chanson etrange",
				"Commentaire en francais", null);
		assertTrue(this.mp3.isFile());
		this.tagger.tag(this.mp3, tag);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes(), HEADER_SIZE));
	}

	@Test
	public void testRemoveTags() throws Throwable {
		TagData tag = new TagData("aa", "2000", "bb", "01", "cc", "dd", null);
		this.tagger.tag(this.mp3, tag);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes(), HEADER_SIZE));
		this.tagger.removeTag(this.mp3);
		assertFalse(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes(), HEADER_SIZE));
	}

	@Test
	public void testTagUTF8() throws Throwable {
		TagData tag = new TagData("héhé", "2000", "aéaé", "01", "féfé", "gégé", null);
		this.tagger.removeTag(this.mp3);
		this.tagger.tag(this.mp3, tag);
		Assert.assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes("UTF-8"), HEADER_SIZE));
	}
}
