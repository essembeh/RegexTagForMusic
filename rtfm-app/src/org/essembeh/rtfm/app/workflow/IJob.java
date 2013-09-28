package org.essembeh.rtfm.app.workflow;

import java.util.List;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IJob {

	List<IResource> getResources();

	void submit(IJobProgressMonitor progressMonitor) throws InterruptedException;

}