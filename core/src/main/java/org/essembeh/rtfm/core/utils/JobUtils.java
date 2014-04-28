package org.essembeh.rtfm.core.utils;

import java.util.concurrent.Semaphore;

import org.essembeh.rtfm.core.workflow.IJob;
import org.essembeh.rtfm.core.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.core.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.core.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class JobUtils {

	public static void synExec(IJob job, final IJobProgressMonitor progressMonitor) throws InterruptedException {
		final Semaphore semaphore = new Semaphore(0);
		job.submit(new IJobProgressMonitor() {
			@Override
			public void resourceDone(ExecutionStatus<IResource, SimpleStatus> s) {
				progressMonitor.resourceDone(s);
			}

			@Override
			public void end(ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> status) {
				progressMonitor.end(status);
				semaphore.release();
			}
		});
		semaphore.acquire();
	}
}
