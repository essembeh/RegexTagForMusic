package org.essembeh.rtfm.app.workflow.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.fs.condition.ICondition;

public class Workflow implements IWorkflow {

	private final String id;
	private final String description;
	private final List<TaskDescription> taskDescriptions;
	private ICondition condition;
	private final boolean auto;
	private final boolean user;

	public Workflow(String id, String description, boolean user, boolean auto) {
		this.id = id;
		this.description = description;
		this.condition = null;
		this.auto = auto;
		this.user = user;
		this.taskDescriptions = new ArrayList<>();
	}

	public void setCondition(ICondition condition) {
		this.condition = condition;
	}

	public void addTaskDescription(TaskDescription taskDescription) {
		this.taskDescriptions.add(taskDescription);
	}

	@Override
	public List<ImmutablePair<TaskDescription, IExecutable>> getExecutables() throws TaskInstanciationException {
		List<ImmutablePair<TaskDescription, IExecutable>> out = new ArrayList<>();
		for (TaskDescription task : taskDescriptions) {
			out.add(ImmutablePair.of(task, task.createInstance()));
		}
		return Collections.unmodifiableList(out);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int compareTo(IWorkflow o) {
		return ObjectUtils.compare(this.getId(), o.getId());
	}

	@Override
	public ICondition getCondition() {
		return condition;
	}

	@Override
	public boolean isAuto() {
		return auto;
	}

	@Override
	public boolean isUser() {
		return user;
	}
}
