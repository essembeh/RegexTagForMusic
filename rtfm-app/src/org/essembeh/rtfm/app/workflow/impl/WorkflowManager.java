package org.essembeh.rtfm.app.workflow.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.TextUtils;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.ITask;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class WorkflowManager implements IWorkflowManager {

	private final static Integer DEFAULT_NBTHREADS = 4;
	private final Map<String, Workflow> workflows;

	public WorkflowManager() {
		this.workflows = new HashMap<>();
	}

	public void addWorkflows(List<Workflow> workflows) {
		for (Workflow workflow : workflows) {
			this.workflows.put(workflow.getId(), workflow);
		}
	}

	public void clear() {
		workflows.clear();
	}

	@Override
	public List<String> getWorkflowIds() {
		return TextUtils.toSortedList(workflows.keySet());
	}

	@Override
	public IWorkflow getWorkflow(String workflowId) {
		return workflows.get(workflowId);
	}

	@Override
	public Collection<? extends IWorkflow> getWorkflows() {
		return workflows.values();
	}

	@Override
	public Collection<? extends IWorkflow> getCompatibleWorkflows(List<IResource> resources) {
		List<Workflow> out = new ArrayList<Workflow>();
		for (Workflow workflow : workflows.values()) {
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
		Workflow workflowImpl = workflows.get(workflow.getId());
		ICondition condition = workflowImpl.getCondition();
		List<ImmutablePair<ITask, IExecutable>> executables = workflowImpl.getExecutables();
		IJob out = new MultiTreadJob(condition, executables, resources, nbThreads);
		return out;
	}

}
