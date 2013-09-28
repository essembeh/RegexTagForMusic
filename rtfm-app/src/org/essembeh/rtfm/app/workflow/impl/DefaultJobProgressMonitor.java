package org.essembeh.rtfm.app.workflow.impl;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class DefaultJobProgressMonitor implements IJobProgressMonitor {

	@Override
	public void start() {
	}

	@Override
	public void end() {
	}

	@Override
	public void error(IResource resource, ExecutionException e) {
	}

	@Override
	public void succeeded(IResource resource) {
	}

	@Override
	public void process(IResource resource) {
	}

	@Override
	public void notSupportedResource(IResource resource) {
	}

}
