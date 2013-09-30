package org.essembeh.rtfm.app.workflow.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

/**
 * 
 * @author seb
 * 
 */
public class MultiTreadJob implements IJob {

	private final ICondition condition;
	private final List<IResource> resources;
	private final List<IExecutable> executables;
	private final int nbThreads;

	public MultiTreadJob(ICondition condition, List<IExecutable> executables, List<IResource> resources, int nbThreads) {
		this.condition = condition;
		this.executables = Collections.unmodifiableList(new ArrayList<>(executables));
		this.resources = Collections.unmodifiableList(new ArrayList<IResource>(resources));
		this.nbThreads = nbThreads;
	}

	@Override
	public List<IResource> getResources() {
		return resources;
	}

	@Override
	public void submit(final IJobProgressMonitor progressMonitor) throws InterruptedException {
		final ExecutorService executor = Executors.newFixedThreadPool(nbThreads);
		progressMonitor.start();
		final CountDownLatch latch = new CountDownLatch(resources.size());
		for (final IResource resource : resources) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					executeOneFile(resource, progressMonitor);
					latch.countDown();
				}
			});
		}
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					latch.await();
				} catch (InterruptedException ignored) {
				}
				progressMonitor.end();
				executor.shutdown();
			}
		});
//		latch.await();
//		executor.shutdown();
//		progressMonitor.end();
	}

	private void executeOneFile(IResource resource, IJobProgressMonitor progressMonitor) {
		if (condition != null && !condition.isTrue(resource)) {
			progressMonitor.notSupportedResource(resource);
		} else {
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
}
