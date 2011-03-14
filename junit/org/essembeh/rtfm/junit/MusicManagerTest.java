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

import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.MusicManager;
import org.essembeh.rtfm.core.Filter.Status;
import org.junit.Before;
import org.junit.Test;

public class MusicManagerTest {

	MusicManager mm;

	File database1 = new File("test/a1.xml");
	File database2 = new File("test/a2.xml");

	File rootFolder = new File("test/a/");

	int totalCount;
	int taggableCount;
	int taggableInvalidCount;
	int taggableValidCount;

	@Before
	public void testConstructor() throws Throwable {
		mm = new MusicManager();
		assertNotNull(mm);
		assertTrue(rootFolder.exists());
		assertTrue(rootFolder.isDirectory());
		mm.scanMusicFolder(rootFolder);
		totalCount = mm.getAllFiles().size();
		System.out.println("Total: " + totalCount);
		taggableCount = mm.getFilteredFiles(Filter.TAGGABLE).size();
		System.out.println("Taggable: " + taggableCount);
		taggableInvalidCount = mm.getFilteredFiles(
				new Filter(Status.INVERSE, Status.ENABLE, Status.NO_FILTER,
						null)).size();
		System.out.println("Taggable invalid: " + taggableInvalidCount);
		taggableValidCount = mm
				.getFilteredFiles(
						new Filter(Status.ENABLE, Status.ENABLE,
								Status.NO_FILTER, null)).size();
		System.out.println("Taggable valid: " + taggableValidCount);
		assertTrue(taggableCount > 0);
		assertTrue(taggableCount > taggableInvalidCount);
		assertTrue(taggableCount == taggableInvalidCount + taggableValidCount);
		assertTrue(totalCount >= taggableCount);
	}

	@Test
	public void testTagDryrun() throws Throwable {
		int errorCount = mm.tagAllTaggableFiles(true);
		assertEquals(taggableCount, mm.getFilteredFiles(Filter.NON_TAGGED)
				.size());
		assertEquals(taggableInvalidCount, errorCount);
	}

	@Test
	public void testTag() throws Throwable {
		int errorCount = mm.tagAllTaggableFiles(false);
		assertEquals(taggableInvalidCount, mm.getFilteredFiles(
				Filter.NON_TAGGED).size());
		assertEquals(taggableInvalidCount, errorCount);
		mm.writeDatabase(database2);
	}

	@Test
	public void testWrite() throws Exception {
		mm.writeDatabase(database1);
	}

	@Test
	public void testClear() throws Exception {
		mm.removeAllMusicFiles();
		assertEquals(0, mm.getAllFiles().size());
	}

	@Test
	public void testRead() throws Exception {
		mm.removeAllMusicFiles();
		mm.readDatabase(database2, true);
		assertEquals(totalCount, mm.getAllFiles().size());
		assertEquals(this.taggableInvalidCount, mm.getFilteredFiles(
				Filter.NON_TAGGED).size());
	}

}
