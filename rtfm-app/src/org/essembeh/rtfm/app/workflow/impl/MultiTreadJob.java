package org.essembeh.rtfm.app.workflow.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.utils.StatusUtils;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.app.workflow.ITask;
import org.essembeh.rtfm.app.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.app.workflow.report.Severity;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.Attributes;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

/**
 * 
 * @author seb
 * 
 */
public class MultiTreadJob implements IJob {

	private final ICondition condition;
	private final List<ImmutablePair<ITask, IExecutable>> executables;
	private final int nbThreads;
	private final List<IResource> resources;
	private final ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> status;

	public MultiTreadJob(	ICondition condition,
							List<ImmutablePair<ITask, IExecutable>> executables,
							List<IResource> resources,
							int nbThreads) {
		this.condition = condition;
		this.executables = Collections.unmodifiableList(new ArrayList<>(executables));
		this.resources = Collections.unmodifiableList(new ArrayList<>(resources));
		this.nbThreads = nbThreads;
		this.status = new ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>>(this);
	}

	@Override
	public void submit(final IJobProgressMonitor progressMonitor) throws InterruptedException {
		status.start();
		final ExecutorService executor = Executors.newFixedThreadPool(nbThreads);
		final CountDownLatch latch = new CountDownLatch(resources.size());
		for (final IResource resource : resources) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					ExecutionStatus<IResource, SimpleStatus> s = executeOneFile(resource);
					status.addStatus(s);
					progressMonitor.resourceDone(s);
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
				status.end();
				progressMonitor.end(status);
				executor.shutdown();
			}
		});
	}

	private ExecutionStatus<IResource, SimpleStatus> executeOneFile(IResource resource) {
		ExecutionStatus<IResource, SimpleStatus> status = new ExecutionStatus<>(resource);
		if (condition != null && !condition.isTrue(resource)) {
			status.addStatus(new SimpleStatus(Severity.WARNING, "Resource not supported"));
		} else {
			Attributes savedAttributes = resource.getAttributes().copy();
			for (ImmutablePair<ITask, IExecutable> p : executables) {
				try {
					int returnCode = p.getRight().execute(resource);
					status.addStatus(StatusUtils.executableEnd(p.getLeft(), returnCode));
				} catch (ExecutionException e) {
					resource.getAttributes().restore(savedAttributes);
					status.addStatus(StatusUtils.executableException(p.getLeft(), e));
					break;
				}
			}
		}
		return status;
	}

	@Override
	public void updateErrorResources() {
		for (ExecutionStatus<IResource, SimpleStatus> s : status.getList()) {
			IResource resource = s.getObject();
			if (s.getSeverity().isKo()) {
				resource.getAttributes().updateError(s.getMessage());
			}
		}
	}

	public ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> getStatus() {
		return status;
	}

	@Override
	public List<IResource> getResources() {
		return Collections.unmodifiableList(resources);
	}
}
