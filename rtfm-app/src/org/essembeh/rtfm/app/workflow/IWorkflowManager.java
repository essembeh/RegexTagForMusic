package org.essembeh.rtfm.app.workflow;

import java.util.Collection;
import java.util.List;

import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IWorkflowManager {

	public abstract List<String> getWorkflowIds();

	public abstract IWorkflow getWorkflow(String workflowId);

	public abstract Collection<? extends IWorkflow> getWorkflows();

	public abstract Collection<? extends IWorkflow> getCompatibleWorkflows(List<IResource> resources);

	public abstract IJob createJob(IWorkflow workflow, List<IResource> resources) throws TaskInstanciationException;

	public abstract IJob createJob(IWorkflow workflow, List<IResource> resources, int nbThreads) throws TaskInstanciationException;

}