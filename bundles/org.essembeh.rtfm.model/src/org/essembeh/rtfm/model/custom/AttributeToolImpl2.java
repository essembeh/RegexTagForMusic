package org.essembeh.rtfm.model.custom;

import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.rtfm.impl.AttributeToolImpl;
import org.essembeh.rtfm.model.utils.AttributesUtils;
import org.essembeh.rtfm.model.utils.StatusUtils;

public class AttributeToolImpl2 extends AttributeToolImpl {

	@Override
	public String toString() {
		return "Update attributes";
	}

	@Override
	public IStatus execute(Node node, IProgressMonitor monitor) {
		MultiStatus out = StatusUtils.newMultistatus("Update attributes of " + node.getVirtualPath());
		for (String attribute : getAttributesToDelete()) {
			if (node.getAttributes().removeKey(attribute) == null) {
				out.add(StatusUtils.newStatus(IStatus.INFO, "Could not remove attribute " + attribute));
			} else {
				out.add(StatusUtils.newStatus(IStatus.OK, "Removed attribute " + attribute));
			}
		}
		for (Entry<String, String> e : getAttributesToUpdate()) {
			node.getAttributes().put(e.getKey(), e.getValue());
			out.add(StatusUtils.newStatus(IStatus.OK, "Set " + AttributesUtils.entryToString(e)));
		}

		return out;
	}

}
