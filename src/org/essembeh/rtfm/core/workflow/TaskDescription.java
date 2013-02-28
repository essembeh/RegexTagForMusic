package org.essembeh.rtfm.core.workflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.essembeh.rtfm.core.utils.commoninterfaces.IConfigurable;
import org.essembeh.rtfm.core.utils.runtime.RuntimeInstanceException;
import org.essembeh.rtfm.core.utils.runtime.RuntimeInstanceFactory;
import org.essembeh.rtfm.tasks.IExecutable;

public class TaskDescription implements IConfigurable {

	private final String id;
	private final String classname;
	private final Map<String, String> properties;

	public TaskDescription(String id, String classname) {
		this.id = id;
		this.classname = classname;
		properties = new HashMap<String, String>();
	}

	@Override
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}

	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @throws RuntimeInstanceException
	 */
	public IExecutable createInstance() throws RuntimeInstanceException {
		IExecutable executable = RuntimeInstanceFactory.createInstance(classname);
		for (Entry<String, String> e : properties.entrySet()) {
			executable.setProperty(e.getKey(), e.getValue());
		}
		return executable;
	}
}
