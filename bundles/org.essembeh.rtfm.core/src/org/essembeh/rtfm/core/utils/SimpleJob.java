package org.essembeh.rtfm.core.utils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.essembeh.rtfm.model.utils.StatusUtils;

public abstract class SimpleJob extends Job {

	private final int work;

	public SimpleJob(String name) {
		this(name, IProgressMonitor.UNKNOWN);
	}

	public SimpleJob(String name, int work) {
		super(name);
		this.work = work;
	}

	public SimpleJob asUserJob(boolean userJob) {
		setUser(userJob);
		setSystem(false);
		return this;
	}

	@Override
	final protected IStatus run(final IProgressMonitor monitor) {
		IStatus out = null;
		monitor.beginTask(getName(), work);
		try {
			out = internalRun(monitor);
		} catch (Exception e) {
			out = StatusUtils.newErrorStatus(e);
		}
		monitor.done();
		return out;
	}

	final public IStatus syncLaunch() throws InterruptedException {
		return syncLaunch(null);
	}

	final public IStatus syncLaunch(SimpleJobListener simpleJobListener) throws InterruptedException {
		asyncLaunch(simpleJobListener);
		join();
		return getResult();
	}

	final public void asyncLaunch() {
		asyncLaunch(null);
	}

	final public void asyncLaunch(SimpleJobListener simpleJobListener) {
		if (simpleJobListener != null) {
			addJobChangeListener(simpleJobListener);
		}
		schedule();
	}

	abstract protected IStatus internalRun(IProgressMonitor monitor) throws Exception;

	protected void checkCancel(IProgressMonitor monitor) throws InterruptedException {
		if (monitor != null && monitor.isCanceled()) {
			throw new InterruptedException();
		}
	}

}
