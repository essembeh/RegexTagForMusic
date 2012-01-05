package org.essembeh.rtfm.core.workflow;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.MusicFile;

public class Workflow {
	private static final Logger logger = Logger.getLogger(Workflow.class);

	List<Task> tasks = new ArrayList<Task>();

	public void addTask(Task task) {
		tasks.add(task);
	}

	public void execute(MusicFile musicFile) throws ActionException {
		logger.debug("Executing workflow on file: " + musicFile);
		for (Task task : tasks) {
			logger.debug("Executing task: " + task);
			task.execute(musicFile);
		}
		logger.debug("End of workflow");
	}

	@Override
	public String toString() {
		return "Workflow [" + tasks.size() + "]";
	}
}
