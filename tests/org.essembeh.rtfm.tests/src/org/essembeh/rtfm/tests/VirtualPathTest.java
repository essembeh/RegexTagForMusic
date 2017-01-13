package org.essembeh.rtfm.tests;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.Stream;

import org.eclipse.core.runtime.Path;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.NodeUtils;
import org.essembeh.rtfm.tests.utils.ResourcesUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class VirtualPathTest {

	private static File file;
	private static File folder;
	private static File unknown;

	@BeforeClass
	public static void init() throws URISyntaxException, IOException {
		folder = ResourcesUtils.getTreeRootFolder();
		Assert.assertTrue(folder.isDirectory());
		file = Stream.of(folder.listFiles()).filter(File::isFile).findFirst().get();
		Assert.assertTrue(file.isFile());
		unknown = new File(folder, "SomeFileWhichDoesNotExist");
		Assert.assertFalse(unknown.exists());
	}

	@Test
	public void testFolder() throws Exception {
		Node n = RtfmCustomFactory.createNode(folder, true);
		Assert.assertEquals(folder.getName(), n.getName());
		n.getContent().put(file.getName(), RtfmCustomFactory.createNode(file, false));
		Assert.assertTrue(NodeUtils.isFolder(n));
	}

	@Test
	public void testUnknown() throws Exception {
		Node n = RtfmCustomFactory.createNode(unknown, true);
		Assert.assertEquals(unknown.getName(), n.getName());
		Assert.assertFalse(NodeUtils.isFolder(n));
		n.getContent().put(file.getName(), RtfmCustomFactory.createNode(file, false));
		Assert.assertTrue(NodeUtils.isFolder(n));
	}

	@Test
	public void testFile() throws Exception {
		Node n = RtfmCustomFactory.createNode(file, true);
		Assert.assertEquals(file.getName(), n.getName());
		Assert.assertFalse(NodeUtils.isFolder(n));
	}

	@Test
	public void testRoot() throws Exception {
		Node root = RtfmCustomFactory.createNode(folder, true);
		Assert.assertEquals("/", root.getVirtualPath());
	}

	@Test
	public void testSub1() throws Exception {
		Node root = ResourcesUtils.getSimpleLibrary().getRoot();

		assertNode(root, "/", true, 4);

		assertNode(root, "/folder/", true, 1);
		assertNode(root, "/file1.mp3", false, 0);
		assertNode(root, "/file2.mp3", false, 0);
		assertNode(root, "/file1.txt", false, 0);

		assertNode(root, "/folder/file1.mp3", false, 0);
	}

	private void assertNode(Node root, String virtualPath, boolean folder, int size) throws IOException {
		Assert.assertNotNull(root);

		Node target = root.find(new Path(virtualPath).makeRelative());
		Assert.assertNotNull(target);

		Assert.assertNotNull(target.getResource());
		Assert.assertTrue(target.getResource().exists());
		Assert.assertEquals(target.getName(), target.getResource().getName());

		Assert.assertEquals(folder, NodeUtils.isFolder(target));
		Assert.assertEquals(folder, target.getResource().isDirectory());
		Assert.assertEquals(folder, target.getVirtualPath().endsWith("/"));

		Assert.assertEquals(size, target.getContent().size());
	}
}
