package org.essembeh.rtfm.core.workflow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.configuration.CoreConfiguration;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.filter.CommonFilters;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.runtime.RuntimeInstanceException;
import org.essembeh.rtfm.tasks.IExecutable;

import com.google.inject.Inject;

public class ExecutionManager {

	private final static Logger LOGGER = Logger.getLogger(ExecutionManager.class);
	private final static Integer DEFAULT_NBTHREADS = 4;
	private final Executor executor;
	private final CoreConfiguration configuration;

	@Inject
	public ExecutionManager(RTFMProperties properties, CoreConfiguration configuration) {
		this.configuration = configuration;
		Integer nbThread = properties.getWithDefault("job.threads", DEFAULT_NBTHREADS);
		LOGGER.debug("Using threads for job: " + nbThread);
		executor = Executors.newFixedThreadPool(nbThread);
	}

	public List<Workflow> getAllWorkflows() {
		return Collections.unmodifiableList(configuration.getWorkflows());
	}

	public List<Workflow> getCompatibleWorkflows(List<IXFile> files) {
		List<Workflow> out = new ArrayList<Workflow>();
		for (Workflow workflow : configuration.getWorkflows()) {
			for (IXFile file : files) {
				if (workflow.supportFile(file)) {
					out.add(workflow);
					break;
				}
			}
		}
		return Collections.unmodifiableList(out);
	}

	public Job createJob(Workflow workflow, List<IXFile> files) throws ConfigurationException, RuntimeInstanceException {
		List<IXFile> filteredFiles = CommonFilters.supportedByWorkflow(workflow).filter(files);
		Job out = new Job(getExecutables(workflow), filteredFiles, executor);
		return out;
	}

	private List<IExecutable> getExecutables(Workflow workflow) throws RuntimeInstanceException, ConfigurationException {
		List<IExecutable> out = new ArrayList<IExecutable>();
		for (String def : workflow.getTaskIds()) {
			TaskDescription taskDescription = findTask(def);
			if (taskDescription == null) {
				throw new ConfigurationException("Cannot find task: " + def);
			}
			out.add(taskDescription.createInstance());
		}
		return Collections.unmodifiableList(out);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	private TaskDescription findTask(String id) {
		for (TaskDescription td : configuration.getTaskDescriptions()) {
			if (td.getId().equals(id)) {
				return td;
			}
		}
		return null;
	}
}
