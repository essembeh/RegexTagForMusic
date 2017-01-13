package org.essembeh.rtfm.model.custom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.impl.NodeEntryImpl;
import org.essembeh.rtfm.model.rtfm.impl.NodeImpl;
import org.essembeh.rtfm.model.utils.NodeUtils;

public class NodeImpl2 extends NodeImpl implements Comparable<Node> {

	private static final String SEPARATOR = "" + Path.SEPARATOR;

	@Override
	public Node getParentNode() {
		if (isRoot()) {
			return null;
		}
		if (eContainer() instanceof NodeEntryImpl && eContainer().eContainer() instanceof Node) {
			return ((Node) eContainer().eContainer());
		}
		return null;
	}

	@Override
	public IPath getPath() {
		if (isRoot()) {
			return new Path(SEPARATOR);
		}
		Node parent = getParentNode();
		if (parent == null) {
			return new Path("???").append(getName());
		}
		return getParentNode().getPath().append(getName());
	}

	@Override
	public String getVirtualPath() {
		if (isRoot()) {
			return SEPARATOR;
		}
		String out = getPath().toString();
		if (isFolder()) {
			out += SEPARATOR;
		}
		return out;
	}

	@Override
	public File getResource() throws IOException {
		String path = getAttributes().get(AttributeConstants.File.FULLPATH);
		if (StringUtils.isBlank(path)) {
			throw new IllegalStateException("Node has no attribute " + AttributeConstants.File.FULLPATH);
		}
		File out = new File(path);
		if (!out.exists()) {
			throw new FileNotFoundException(path);
		}
		return out;
	}

	@Override
	public String toString() {
		return getVirtualPath();
	}

	@Override
	public Node find(IPath path) {
		if (path.isEmpty()) {
			return this;
		}
		if (path.isAbsolute() && !isRoot()) {
			return getParentNode().find(path);
		}
		Node element = getContent().get(path.segment(0));
		if (element != null) {
			return element.find(path.removeFirstSegments(1));
		}
		return null;
	}

	@Override
	public int compareTo(Node o) {
		return NodeUtils.compare(this, o);
	}
}
