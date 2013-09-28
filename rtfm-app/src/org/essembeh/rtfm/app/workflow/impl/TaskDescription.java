package org.essembeh.rtfm.app.workflow.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.utils.IConfigurable;
import org.essembeh.rtfm.app.utils.InstanceUtils;
import org.essembeh.rtfm.app.workflow.IExecutable;

public class TaskDescription implements IConfigurable {

	/**
	 * Attributes
	 */
	private final String id;
	private final String classname;
	private final List<Pair<String, String>> properties;

	public TaskDescription(String id, String classname) {
		this.id = id;
		this.classname = classname;
		properties = new ArrayList<>();
	}

	@Override
	public void setProperty(String key, String value) {
		properties.add(Pair.of(key, value));
	}

	public String getId() {
		return id;
	}

	public IExecutable createInstance() throws TaskInstanciationException {
		IExecutable executable;
		try {
			executable = InstanceUtils.createInstance(classname);
			for (Pair<String, String> e : properties) {
				executable.setProperty(e.getKey(), e.getValue());
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new TaskInstanciationException(this, e);
		}
		return executable;
	}
}
