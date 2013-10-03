package org.essembeh.rtfm.app.utils;

import java.util.concurrent.Semaphore;

import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.app.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;
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
