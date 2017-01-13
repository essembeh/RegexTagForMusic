package org.essembeh.rtfm.ui.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.actions.SelectionProviderAction;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.StatusUtils;
import org.essembeh.rtfm.ui.SharedImages;

public class XdgOpenAction extends SelectionProviderAction {

	public XdgOpenAction(ISelectionProvider selectionProvider) {
		super(selectionProvider, "Open ...");
		// FIXME Image
		setImageDescriptor(SharedImages.getImageDescriptor(SharedImages.FIND));
	}

	protected List<Node> getSelectedNodes() {
		List<Node> out = new ArrayList<>();
		for (Object o : getStructuredSelection().toArray()) {
			if (o instanceof Node) {
				out.add((Node) o);
			}
		}
		return out;
	}

	@Override
	public void run() {
		if (getStructuredSelection().getFirstElement() instanceof Node) {
			Node sel = (Node) getStructuredSelection().getFirstElement();
			try {
				new ProcessBuilder("xdg-open", sel.getAttributes().get(AttributeConstants.File.FULLPATH)).start().waitFor();
			} catch (IOException | InterruptedException e) {
				ErrorDialog.openError(null, "Error", "Cannot open files", StatusUtils.newErrorStatus(e));
			}
		}
	}
}
