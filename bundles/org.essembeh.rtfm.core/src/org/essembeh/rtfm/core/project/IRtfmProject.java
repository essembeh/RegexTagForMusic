package org.essembeh.rtfm.core.project;

import java.io.IOException;

import org.eclipse.core.resources.IProject;
import org.essembeh.rtfm.model.rtfm.Configuration;

public interface IRtfmProject {

	IProject getProject();

	Configuration getActiveConfiguration() throws IOException;

}