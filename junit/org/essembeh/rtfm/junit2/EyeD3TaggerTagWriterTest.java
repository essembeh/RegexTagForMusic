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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.essembeh.rtfm.core.configuration.interfaces.IAction;
import org.essembeh.rtfm.core.tag.TagData;
import org.essembeh.rtfm.junit.utils.BinaryUtils;
import org.essembeh.rtfm.tasks.ExternalProcess;
import org.junit.Before;
import org.junit.Test;

public class EyeD3TaggerTagWriterTest {

	Workflow tagger23 = null;
	Workflow tagger24 = null;
	File mp3 = new File("res/test/notag.mp3");
	TagData tag = null;

	@Before
	public void init() {
		assertTrue(this.mp3.exists());
		assertTrue(this.mp3.isFile());
		this.tag = new TagData("SuperArtistéàè", "2000", "SuperMotif", "01", "cc", "dd", null);
		this.tagger23 = new ExternalProcess();
		this.tagger23.setProperty("script.tag", "scripts/eyeD3-tag.sh");
		this.tagger23.setProperty("script.removetag", "scripts/eyeD3-remove.sh");
		this.tagger23.setProperty("env.set", "TAG_VERSION=ID3V2_3");
		this.tagger24 = new ExternalProcess();
		this.tagger24.setProperty("script.tag", "scripts/eyeD3-tag.sh");
		this.tagger24.setProperty("script.removetag", "scripts/eyeD3-remove.sh");
		this.tagger24.setProperty("env.set", "TAG_VERSION=ID3V2_4");
		this.tagger24.setProperty("env.set", "TAG_FORCEUTF8=true");
	}

	@Test
	public void testTag1() throws Throwable {
		String motif = this.tag.getAlbum();
		this.tagger23.removeTag(this.mp3);
		assertFalse(BinaryUtils.checkFileContainsBytes(this.mp3, motif.getBytes()));
		this.tagger23.tag(this.mp3, this.tag);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, motif.getBytes()));
	}

	@Test
	public void testTag2() throws Throwable {
		String motif = this.tag.getAlbum();
		this.tagger24.removeTag(this.mp3);
		assertFalse(BinaryUtils.checkFileContainsBytes(this.mp3, motif.getBytes()));
		this.tagger24.tag(this.mp3, this.tag);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, motif.getBytes()));
	}

	@Test
	public void testRemoveTag1() throws Throwable {
		String motif = this.tag.getAlbum();
		this.tagger23.tag(this.mp3, this.tag);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, motif.getBytes()));
		this.tagger23.removeTag(this.mp3);
		assertFalse(BinaryUtils.checkFileContainsBytes(this.mp3, motif.getBytes()));
	}

	@Test
	public void testRemoveTag2() throws Throwable {
		String motif = this.tag.getAlbum();
		this.tagger24.tag(this.mp3, this.tag);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, motif.getBytes()));
		this.tagger24.removeTag(this.mp3);
		assertFalse(BinaryUtils.checkFileContainsBytes(this.mp3, motif.getBytes()));
	}

	@Test
	public void testUTF8() throws Throwable {
		this.tagger24.tag(this.mp3, this.tag);
		assertTrue(BinaryUtils.checkFileContainsBytes(this.mp3, this.tag.getArtist().getBytes()));
	}
}
