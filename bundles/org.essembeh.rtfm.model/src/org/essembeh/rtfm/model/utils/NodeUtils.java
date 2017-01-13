package org.essembeh.rtfm.model.utils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.rtfm.Library;
import org.essembeh.rtfm.model.rtfm.Node;

public class NodeUtils {

	public static int compare(Node a, Node b) {
		if (isFolder(a) && !isFolder(b)) {
			return -1;
		}
		if (isFolder(b) && !isFolder(a)) {
			return 1;
		}
		return a.getName().compareTo(b.getName());
	}

	public static List<Node> filterNodes(Node node, Predicate<Node> predicate) {
		return recursiveStream(node).filter(predicate).collect(Collectors.toList());
	}

	public static Stream<Node> recursiveStream(Node in) {
		return Stream.concat(Stream.of(in), in.getContent().values().stream().flatMap(NodeUtils::recursiveStream));
	}

	public static void visit(Library library, Consumer<Node> consumer) {
		visit(library.getRoot(), consumer);
	}

	public static void visit(Node in, Consumer<Node> consumer) {
		recursiveStream(in).forEach(consumer);
	}

	public static boolean isFolder(Node in) {
		String value = in.getAttributes().get(AttributeConstants.File.TYPE);
		if (value == null) {
			return !in.getContent().isEmpty();
		}
		if (AttributeConstants.File.TYPE_FOLDER.equals(value)) {
			return true;
		}
		return false;
	}

	public static Library getLibrary(Node in) {
		Node parent = in.getParentNode();
		if (parent != null) {
			return getLibrary(parent);
		} else if (in.isRoot()) {
			if (in.eContainer() instanceof Library) {
				return (Library) in.eContainer();
			}
		}
		return null;
	}
}
