package org.essembeh.rtfm.core.utils;

import java.util.function.BiConsumer;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;

public class SimpleJobListener implements IJobChangeListener {

	private final BiConsumer<Job, IStatus> callback;

	public SimpleJobListener(BiConsumer<Job, IStatus> callback) {
		this.callback = callback;
	}

	@Override
	public void aboutToRun(IJobChangeEvent event) {
	}

	@Override
	public void awake(IJobChangeEvent event) {
	}

	@Override
	final public void done(final IJobChangeEvent event) {
		callback.accept(event.getJob(), event.getResult());
	}

	@Override
	public void running(IJobChangeEvent event) {
	}

	@Override
	public void scheduled(IJobChangeEvent event) {
	}

	@Override
	public void sleeping(IJobChangeEvent event) {
	}
}
