package org.essembeh.rtfm.core.actions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class Action {
	private static final Logger logger = Logger.getLogger(Action.class);

	private String name;
	private String identifier;
	private List<String> applyOnTypes;
	private List<TaskExecutor> tasks;

	public Action(String name, String identifier) {
		this.name = name;
		this.identifier = identifier;
		applyOnTypes = new ArrayList<String>();
		tasks = new ArrayList<TaskExecutor>();
	}

	public void addSupportedType(String type) {
		applyOnTypes.add(type);
	}

	public void addTask(TaskExecutor task) {
		tasks.add(task);
	}

	public boolean supportType(FileType type) {
		return applyOnTypes.contains(type.getIdentifier());
	}

	public void executeOn(IMusicFile musicFile) throws ActionException {
		logger.debug("Executing workflow on file: " + musicFile);
		for (TaskExecutor task : tasks) {
			logger.debug("Executing task: " + task);
			task.execute(musicFile);
		}
		logger.debug("End of workflow");
	}

	public String getName() {
		return name;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return "Action [name=" + name + ", identifier=" + identifier + ", applyOnTypes=" + applyOnTypes + ", tasks="
				+ tasks.size() + "]";
	}
}
