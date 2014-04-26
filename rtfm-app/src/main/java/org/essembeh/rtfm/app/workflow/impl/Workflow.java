package org.essembeh.rtfm.app.workflow.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.id.Identified;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.app.workflow.ITask;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.fs.condition.ICondition;

public class Workflow extends Identified implements IWorkflow {

	private final String description;
	private final ICondition condition;
	private final boolean auto;
	private final boolean user;
	private final List<CustomTaskDescription> customTaskDescriptions;

	/**
	 * 
	 * @param id
	 * @param description
	 * @param condition
	 * @param user
	 * @param auto
	 */
	public Workflow(String id, String description, ICondition condition, boolean user, boolean auto) {
		super(id);
		this.description = description;
		this.condition = condition;
		this.auto = auto;
		this.user = user;
		this.customTaskDescriptions = new ArrayList<>();
	}

	@Override
	public String getDescription() {
		return description;
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

	public void addCustomTaskDescription(CustomTaskDescription customTaskDescription) {
		customTaskDescriptions.add(customTaskDescription);
	}

	@Override
	public List<Pair<ITask, IExecutable>> getExecutables() throws TaskInstanciationException {
		List<Pair<ITask, IExecutable>> out = new ArrayList<>();
		for (CustomTaskDescription customTaskDescription : customTaskDescriptions) {
			ITask task = customTaskDescription.getTaskDescription();
			IExecutable executable = customTaskDescription.createInstance();
			out.add(ImmutablePair.of(task, executable));
		}
		return Collections.unmodifiableList(out);
	}
}
