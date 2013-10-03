package org.essembeh.rtfm.app.workflow;

import org.essembeh.rtfm.app.workflow.report.ExecutionStatus;
import org.essembeh.rtfm.app.workflow.report.SimpleStatus;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IJobProgressMonitor {

	void resourceDone(ExecutionStatus<IResource, SimpleStatus> s);

	void end(ExecutionStatus<IJob, ExecutionStatus<IResource, SimpleStatus>> status);

}
