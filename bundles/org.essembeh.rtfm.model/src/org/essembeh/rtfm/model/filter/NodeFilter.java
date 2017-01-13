package org.essembeh.rtfm.model.filter;

import java.util.function.Predicate;

import org.essembeh.rtfm.model.rtfm.Node;

public class NodeFilter implements INodeFilter {

	private final Predicate<Node> predicate;
	private final String text;

	public NodeFilter(Predicate<Node> predicate, String text) {
		this.predicate = predicate;
		this.text = text;
	}

	@Override
	public boolean test(Node t) {
		return predicate.test(t);
	}

	@Override
	public String toString() {
		return text;
	}
}
