package org.essembeh.rtfm.ui.adapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.rtfm.Node;

public class NodePropertySource implements IPropertySource {

	private static final List<String> DATE_FIELDS = Arrays.asList(AttributeConstants.App.IMPORTED, AttributeConstants.App.LAST_SCAN, AttributeConstants.File.LAST_MODIFIED);
	private final static String VIRTUALPATH_ID = "VIRTUALPATH";
	private final static String VIRTUALPATH_LABEL = "Virtual path";
	private final Node node;

	public NodePropertySource(Node node) {
		this.node = node;
	}

	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		List<IPropertyDescriptor> out = new ArrayList<>();
		out.add(new PropertyDescriptor(VIRTUALPATH_ID, VIRTUALPATH_LABEL));
		for (String e : node.getAttributes().keySet()) {
			out.add(new PropertyDescriptor(e, e));
		}
		return out.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (VIRTUALPATH_ID.equals(id)) {
			return node.getVirtualPath();
		}
		String out = node.getAttributes().get(id);
		if (AttributeConstants.File.SIZE.equals(id)) {
			out = FileUtils.byteCountToDisplaySize(Long.parseLong(out));
		} else if (DATE_FIELDS.contains(id)) {
			out = new Date(Long.parseLong(out)).toString();
		}
		return out;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return VIRTUALPATH_ID.equals(id) || node.getAttributes().containsKey(id);
	}

	@Override
	public void resetPropertyValue(Object id) {
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (!VIRTUALPATH_ID.equals(id)) {
			node.getAttributes().put((String) id, (String) value);
		}
	}

}
