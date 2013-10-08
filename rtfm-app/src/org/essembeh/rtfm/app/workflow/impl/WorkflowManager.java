package org.essembeh.rtfm.app.workflow.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.id.IdUtils;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.ITask;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class WorkflowManager implements IWorkflowManager {

	private final static Integer DEFAULT_NBTHREADS = 4;
	private final List<IWorkflow> workflows;

	public WorkflowManager() {
		this.workflows = new ArrayList<>();
	}

	public void addWorkflows(List<Workflow> workflows) {
		this.workflows.addAll(workflows);
	}

	public void clear() {
		workflows.clear();
	}

	@Override
	public List<String> getWorkflowIds() {
		return Collections.unmodifiableList(IdUtils.getIds(workflows));
	}

	@Override
	public IWorkflow getWorkflow(String workflowId) {
		return IdUtils.getById(workflows, workflowId);
	}

	@Override
	public List<IWorkflow> getWorkflows() {
		return Collections.unmodifiableList(workflows);
	}

	@Override
	public List<IWorkflow> getCompatibleWorkflows(List<IResource> resources) {
		List<IWorkflow> out = new ArrayList<>();
		for (IWorkflow workflow : workflows) {
			for (IResource resource : resources) {
				if (workflow.getCondition() == null || workflow.getCondition().isTrue(resource)) {
					out.add(workflow);
					break;
				}
			}
		}
		return Collections.unmodifiableList(out);
	}

	@Override
	public IJob createJob(IWorkflow workflow, List<IResource> resources) throws TaskInstanciationException {
		return createJob(workflow, resources, DEFAULT_NBTHREADS);
	}

	@Override
	public IJob createJob(IWorkflow workflow, List<IResource> resources, int nbThreads)
			throws TaskInstanciationException {
		ICondition condition = workflow.getCondition();
		List<ImmutablePair<ITask, IExecutable>> executables = workflow.getExecutables();
		IJob out = new MultiTreadJob(condition, executables, resources, nbThreads);
		return out;
	}

}
