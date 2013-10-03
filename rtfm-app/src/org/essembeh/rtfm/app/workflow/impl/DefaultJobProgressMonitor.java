package org.essembeh.rtfm.app.workflow.impl;

import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IJobProgressMonitor;
import org.essembeh.rtfm.app.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class DefaultJobProgressMonitor implements IJobProgressMonitor {

	@Override
	public void resourceDone(ExecutionStatus<IResource, SimpleStatus> s) {
	}

	@Override
	public void end(ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> status) {
	}

}
