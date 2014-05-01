package org.essembeh.rtfm.core.workflow.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.tuple.Pair;
import org.essembeh.rtfm.core.exception.ExecutionException;
import org.essembeh.rtfm.core.utils.StatusUtils;
import org.essembeh.rtfm.core.workflow.IExecutable;
import org.essembeh.rtfm.core.workflow.IJob;
import org.essembeh.rtfm.core.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.core.workflow.ITask;
import org.essembeh.rtfm.core.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.core.workflow.report.Severity;
import org.essembeh.rtfm.core.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.AttributesHelper;

/**
 * 
 * @author seb
 * 
 */
public class MultiTreadJob implements IJob {

	private final ICondition condition;
	private final List<Pair<ITask, IExecutable>> executables;
	private final int nbThreads;
	private final List<IResource> resources;
	private final ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> status;

	public MultiTreadJob(	ICondition condition,
							List<Pair<ITask, IExecutable>> executables,
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
			Map<String, String> savedAttributes = AttributesHelper.cloneAttributes(resource);
			for (Pair<ITask, IExecutable> p : executables) {
				SimpleStatus currentStatus = null;
				try {
					int returnCode = p.getRight().execute(resource);
					currentStatus = StatusUtils.executableEnd(p.getLeft(), returnCode);
				} catch (ExecutionException e) {
					currentStatus = StatusUtils.executableException(p.getLeft(), e);
				}
				status.addStatus(currentStatus);
				if (currentStatus.getSeverity().isKo()) {
					AttributesHelper.resetAttributes(resource, savedAttributes);
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
				AttributesHelper.updateError(resource, s.getMessage());
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
