package org.essembeh.rtfm.core.workflow.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.essembeh.rtfm.core.exception.TaskInstanciationException;
import org.essembeh.rtfm.core.utils.Configurator;
import org.essembeh.rtfm.core.utils.InstanceUtils;
import org.essembeh.rtfm.core.utils.id.Identifiable;
import org.essembeh.rtfm.core.workflow.IExecutable;
import org.essembeh.rtfm.core.workflow.ITask;

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

	@Override
	public int compareTo(Identifiable o) {
		return ObjectUtils.compare(id, o.getId());
	}
}
