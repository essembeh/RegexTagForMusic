package org.essembeh.rtfm.core.project.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdapterFactory;
import org.essembeh.rtfm.core.project.IRtfmProject;

public class RtfmProjectAdapterFactory implements IAdapterFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (adaptableObject instanceof IProject && IRtfmProject.class.equals(adapterType)) {
			return (T) new RtfmProject((IProject) adaptableObject);
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class[] { IRtfmProject.class };
	}

}
