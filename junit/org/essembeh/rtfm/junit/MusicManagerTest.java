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
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.interfaces.IMusicFile;
import org.junit.Before;
import org.junit.Test;

public class MusicManagerTest {

	MusicManager mm;

	File database1 = new File("test/default1.xml");
	File database2 = new File("test/default2.xml");

	File rootFolder = new File("test/default/");

	int totalCount;
	int taggableCount;

	@Before
	public void testConstructor() throws Throwable {
		this.mm = new MusicManager();
		assertNotNull(this.mm);
		assertTrue(this.rootFolder.exists());
		assertTrue(this.rootFolder.isDirectory());
		this.mm.scanMusicFolder(this.rootFolder);
		this.totalCount = this.mm.getAllFiles().size();
		System.out.println("Total: " + this.totalCount);
		this.taggableCount = this.mm.getFilteredFiles(Filter.TAGGABLE).size();
		assertTrue(this.taggableCount > 0);
		assertTrue(this.totalCount >= this.taggableCount);
	}

	@Test
	public void testTag() throws Throwable {
		List<IMusicFile> list = this.mm.getFilteredFiles(Filter.TAGGABLE);
		int errorCount = 0;
		for (IMusicFile musicFile : list) {
			try {
				musicFile.tag();
			} catch (Exception e) {
				errorCount++;
			}
		}
		assertEquals(0, this.mm.getFilteredFiles(Filter.NON_TAGGED).size());
		assertEquals(0, errorCount);
		this.mm.writeDatabase(this.database2);
	}

	@Test
	public void testWrite() throws Exception {
		this.mm.writeDatabase(this.database1);
	}

	@Test
	public void testClear() throws Exception {
		this.mm.clear();
		assertEquals(0, this.mm.getAllFiles().size());
	}

	@Test
	public void testRead() throws Exception {
		this.mm.clear();
		this.mm.readDatabase(this.database2, true);
		assertEquals(this.totalCount, this.mm.getAllFiles().size());
		assertEquals(0, this.mm.getFilteredFiles(Filter.NON_TAGGED).size());
	}

}
