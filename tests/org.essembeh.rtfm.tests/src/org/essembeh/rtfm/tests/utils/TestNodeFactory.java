package org.essembeh.rtfm.tests.utils;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.rtfm.Node;
import org.junit.Assert;

public class TestNodeFactory {

	public static Node createNode(IPath path, boolean folder) {
		if (path.isEmpty() || path.isRoot()) {
			return RtfmCustomFactory.createNode("root", true, true);
		}
		String name = path.lastSegment();
		Node out = RtfmCustomFactory.createNode(name, false, folder);
		Node parent = createNode(path.removeLastSegments(1), true);
		parent.getContent().put(name, out);
		Assert.assertTrue(parent == out.getParentNode());
		return out;
	}

	public static Node createNode(String virtualPath) {
		Node out = createNode(new Path(virtualPath), virtualPath.endsWith("/"));
		Assert.assertEquals(virtualPath, out.getVirtualPath());
		return out;
	}
}
