package org.essembeh.rtfm.ui.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;
import org.essembeh.rtfm.model.rtfm.Node;

public class NodeAdapterFactory implements IAdapterFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adaptableObject instanceof Node && IPropertySource.class.equals(adapterType)) {
			return (T) new NodePropertySource((Node) adaptableObject);
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] { Node.class };
	}

}
