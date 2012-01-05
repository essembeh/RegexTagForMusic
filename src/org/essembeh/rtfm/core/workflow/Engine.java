package org.essembeh.rtfm.core.workflow;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.MusicFile;

public class Engine implements Iterable<String> {

	private static Logger logger = Logger.getLogger(Engine.class);

	Map<String, Workflow> map;

	public Engine() {
		map = new HashMap<String, Workflow>();
	}

	public void createWorkflow(String name, Task... tasks) {
		Workflow workflow = new Workflow();
		for (int i = 0; i < tasks.length; i++) {
			workflow.addTask(tasks[i]);
		}
		map.put(name, workflow);
	}

	public void execute(String action, MusicFile musicFile) throws ActionException {
		if (!map.containsKey(action)) {
			throw new ActionException("Action not found: " + action);
		}
		Workflow workflow = map.get(action);
		logger.debug("Executing action: " + action + ", on file: " + musicFile);
		workflow.execute(musicFile);
	}

	public Set<String> getActions() {
		return map.keySet();
	}

	@Override
	public Iterator<String> iterator() {
		return map.keySet().iterator();
	}
}
