package org.essembeh.rtfm.ui.widgets;

import java.util.function.Function;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.ui.utils.ImagesUtils;

public class LibraryLabelProvider extends LabelProvider {

	private final Function<Node, String> toText;

	public LibraryLabelProvider(Function<Node, String> toText) {
		this.toText = toText;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Node) {
			return toText.apply((Node) element);
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof Node) {
			return ImagesUtils.getNodeImage((Node) element);
		}
		return super.getImage(element);
	}
}
