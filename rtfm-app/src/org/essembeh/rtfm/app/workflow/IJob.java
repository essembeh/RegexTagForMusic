package org.essembeh.rtfm.app.workflow;

import java.util.List;

import org.essembeh.rtfm.app.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IJob {

	void submit(IJobProgressMonitor progressMonitor) throws InterruptedException;

	List<IResource> getResources();

	ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> getStatus();

	void updateErrorResources();
}
