package org.essembeh.rtfm.app.workflow.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

/**
 * 
 * @author seb
 * 
 */
public class OneThreadJob implements IJob {

	private final List<IResource> resources;
	private final List<IExecutable> executables;

	public OneThreadJob(List<IExecutable> executables, List<IResource> resources) {
		this.executables = Collections.unmodifiableList(new ArrayList<>(executables));
		this.resources = Collections.unmodifiableList(new ArrayList<IResource>(resources));
	}

	@Override
	public List<IResource> getResources() {
		return resources;
	}

	@Override
	public void submit(final IJobProgressMonitor progressMonitor) throws InterruptedException {
		progressMonitor.start();
		for (final IResource resource : resources) {
			executeOneFile(resource, progressMonitor);
		}
		progressMonitor.end();
	}

	private void executeOneFile(IResource resource, IJobProgressMonitor progressMonitor) {
		progressMonitor.process(resource);
		try {
			for (IExecutable executable : executables) {
				executable.execute(resource);
			}
			progressMonitor.succeeded(resource);
		} catch (ExecutionException e) {
			progressMonitor.error(resource, e);
		}
	}
}
