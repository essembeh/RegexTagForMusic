package org.essembeh.rtfm.app.workflow.impl;

import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class WorkflowSupportCondition implements ICondition {

	private final IWorkflow workflow;

	public WorkflowSupportCondition(IWorkflow workflow) {
		this.workflow = workflow;
	}

	@Override
	public boolean isTrue(IResource resource) {
		return workflow.getCondition().isTrue(resource);
	}

}
