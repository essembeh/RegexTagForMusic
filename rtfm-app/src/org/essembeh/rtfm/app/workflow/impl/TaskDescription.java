package org.essembeh.rtfm.app.workflow.impl;

import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.Configurator;
import org.essembeh.rtfm.app.utils.InstanceUtils;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.app.workflow.ITask;

public class TaskDescription extends Configurator<IExecutable> implements ITask {

	/**
	 * Attributes
	 */
	private final String id;
	private final String classname;

	public TaskDescription(String id, String classname) {
		this.id = id;
		this.classname = classname;
	}

	@Override
	public String getId() {
		return id;
	}

	public IExecutable createInstance() throws TaskInstanciationException {
		IExecutable executable;
		try {
			executable = InstanceUtils.createInstance(classname);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new TaskInstanciationException(this, e);
		}
		return getConfiguredObject(executable);
	}
}
