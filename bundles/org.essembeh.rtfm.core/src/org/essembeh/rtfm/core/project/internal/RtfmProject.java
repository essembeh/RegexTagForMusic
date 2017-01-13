package org.essembeh.rtfm.core.project.internal;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.essembeh.rtfm.core.project.IRtfmProject;
import org.essembeh.rtfm.core.utils.RtfmModelIO;
import org.essembeh.rtfm.model.rtfm.Configuration;

public class RtfmProject implements IRtfmProject, IAdaptable {

	private final IProject project;

	public RtfmProject(IProject project) {
		this.project = project;
	}

	@Override
	public IProject getProject() {
		return project;
	}

	@Override
	public Configuration getActiveConfiguration() throws IOException {
		return RtfmModelIO.loadConfiguration(project.getFile("default.config").getLocation().toFile());
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		if (IProject.class.equals(adapter)) {
			return (T) project;
		}
		return null;
	}
}
