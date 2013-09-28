package org.essembeh.rtfm.app.workflow;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IJobProgressMonitor {

	void start();

	void end();

	void error(IResource resource, ExecutionException e);

	void succeeded(IResource resource);

	void process(IResource resource);

	void notSupportedResource(IResource resource);

}
