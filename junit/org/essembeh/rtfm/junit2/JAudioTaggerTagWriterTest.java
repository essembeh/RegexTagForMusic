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

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.essembeh.rtfm.core.configuration.interfaces.IAction;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.junit.utils.BinaryUtils;
import org.essembeh.rtfm.tasks.JAudioTagger;
import org.junit.Before;
import org.junit.Test;

public class JAudioTaggerTagWriterTest {

	Workflow tagger;

	File mp3 = new File("res/test/notag.mp3");

	@Before
	public void init() {
		this.tagger = new JAudioTagger();
		assertTrue(this.mp3.exists());
		assertTrue(this.mp3.isFile());
	}

	@Test
	public void testRemoveTags() throws Throwable {
		TagData tag = new TagData("SuperArtist", "2000", "bb", "01", "cc", "dd", null);
		this.tagger.tag(this.mp3, tag);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, tag.getArtist().getBytes()));
		this.tagger.removeTag(this.mp3);
		// TODO: removing tags with JAudioTagger does not remove DATA, just
		// wipes ID3 header.
		// assertFalse(BinaryUtils.checkFileContainsBytes(this.mp3,
		// tag.getArtist().getBytes(), HEADER_SIZE));
	}
}
