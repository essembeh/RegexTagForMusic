package org.essembeh.rtfm.tests;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.Path;
import org.essembeh.rtfm.core.configuration.ConfigurationManager;
import org.essembeh.rtfm.core.controller.LibraryScanner;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.filter.INodeFilter;
import org.essembeh.rtfm.model.filter.NodeFilterFactory;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.RtfmFactory;
import org.essembeh.rtfm.model.utils.AttributesUtils;
import org.essembeh.rtfm.model.utils.NodeUtils;
import org.essembeh.rtfm.model.utils.StatusUtils;
import org.essembeh.rtfm.tests.utils.ResourcesUtils;
import org.junit.Assert;
import org.junit.Test;

public class SebLibraryTest {

	public static final int MP3_ALBUM_COUNT = 9;
	public static final int COVER_ALBUM_COUNT = 2;
	public static final int SAGA_ALBUM_COUNT = 4;
	public static final int SINGLES_ALBUM_COUNT = 2;

	public static final int MP3_COUNT = MP3_ALBUM_COUNT + SAGA_ALBUM_COUNT + SINGLES_ALBUM_COUNT;
	public static final int FILE_COUNT = MP3_COUNT + COVER_ALBUM_COUNT;
	public static final int FOLDER_COUNT = 10;

	public static final int NODE_COUNT = FILE_COUNT + FOLDER_COUNT;

	public static final INodeFilter MP3_PREDICATE = NodeFilterFactory.attributeEquals(AttributeConstants.File.EXTENSION, "mp3");

	public static Library getLibrary() throws URISyntaxException, IOException {
		Library library = RtfmFactory.eINSTANCE.createLibrary();
		library.setPath(ResourcesUtils.getSebRootFolder().getAbsolutePath());
		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, ConfigurationManager.INSTANCE.getDefault(), null)));
		Assert.assertNotNull(library.getRoot());
		return library;
	}

	@Test
	public void testScan() throws Exception {
		Library library = getLibrary();

		Assert.assertTrue(NodeUtils.isFolder(library.getRoot()));
		Assert.assertFalse(library.getRoot().getContent().isEmpty());

		NodeUtils.recursiveStream(library.getRoot()).forEach(this::checkNode);
		NodeUtils.recursiveStream(library.getRoot()).forEach(this::checkAttributes);
	}

	@Test
	public void testRescan() throws Exception {
		Library library = getLibrary();

		Node root = library.getRoot();
		Assert.assertEquals(3, root.getContent().size());

		Node album = root.find(new Path("Albums"));
		Assert.assertNotNull(album);

		Node newNode = RtfmCustomFactory.createNode("foo", false, true);
		root.getContent().put("foo", newNode);
		Assert.assertEquals(4, root.getContent().size());

		Assert.assertTrue(StatusUtils.hasNoError(LibraryScanner.refresh(library, ConfigurationManager.INSTANCE.getDefault(), null)));
		Assert.assertEquals(3, root.getContent().size());
		Assert.assertSame(root, library.getRoot());
		Assert.assertSame(album, library.getRoot().find(new Path("Albums")));
		Assert.assertNull(newNode.getParentNode());
	}

	@Test
	public void testFilter() throws Exception {
		Library library = getLibrary();

		Assert.assertEquals(NODE_COUNT, NodeUtils.recursiveStream(library.getRoot()).count());
		Assert.assertEquals(FILE_COUNT, NodeUtils.recursiveStream(library.getRoot()).filter(NodeFilterFactory.FILE).count());
		Assert.assertEquals(FOLDER_COUNT, NodeUtils.recursiveStream(library.getRoot()).filter(NodeFilterFactory.FOLDER).count());

		Assert.assertEquals(MP3_COUNT, NodeUtils.recursiveStream(library.getRoot()).filter(MP3_PREDICATE).count());
		Assert.assertEquals(MP3_COUNT, NodeUtils.filterNodes(library.getRoot(), MP3_PREDICATE).size());
	}

	@Test
	public void testSearch() throws Exception {
		Library library = getLibrary();

		Node root = library.getRoot();
		Node sub = root.find(new Path("Albums/Alice/2001 - Second Album/02 - Track.mp3"));
		Assert.assertNotNull(sub);
		Assert.assertFalse(NodeUtils.isFolder(sub));
	}

	private void checkAttributes(Node in) {
		if (!NodeUtils.isFolder(in)) {
			Assert.assertFalse(in.getAttributes().isEmpty());

			Assert.assertTrue(in.getAttributes().containsKey(AttributeConstants.File.EXTENSION));
			String extension = in.getAttributes().get(AttributeConstants.File.EXTENSION);
			Assert.assertTrue(StringUtils.isNotBlank(extension));
			Assert.assertTrue(in.getVirtualPath().endsWith(extension));

			Assert.assertTrue(in.getAttributes().containsKey(AttributeConstants.File.LAST_MODIFIED));
			Assert.assertTrue(AttributesUtils.getLong(in, AttributeConstants.File.LAST_MODIFIED) > 0);

			Assert.assertTrue(in.getAttributes().containsKey(AttributeConstants.File.SIZE));
			Assert.assertTrue(AttributesUtils.getLong(in, AttributeConstants.File.SIZE) >= 0);

			Assert.assertFalse(in.getAttributes().containsKey(AttributeConstants.App.ERROR));
		}
	}

	private void checkNode(Node in) {
		try {
			File f = in.getResource();
			Assert.assertTrue(f.exists());
			Assert.assertEquals(in.getName(), f.getName());
			Assert.assertEquals(NodeUtils.isFolder(in), f.isDirectory());
			Assert.assertEquals(NodeUtils.isFolder(in), in.getVirtualPath().endsWith("/"));
			if (!NodeUtils.isFolder(in)) {
				Assert.assertTrue(in.getContent().isEmpty());
			}
			if (!in.getPath().isRoot()) {
				Assert.assertTrue(f.getAbsolutePath().endsWith(in.getPath().toString()));
			}
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}
}
