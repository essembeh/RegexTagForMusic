package org.essembeh.rtfm.core.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.IJob;
import org.essembeh.rtfm.core.actions.Job;
import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.configuration.io.ICoreConfigurationLoader;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

import com.google.inject.Inject;

/**
 * 
 * @author seb
 * 
 */
public class ActionService {

	private static final int DEFAULT_NBTHREADS = 4;

	private static Logger logger = Logger.getLogger(ActionService.class);

	private final IdList<Workflow, Identifier<Workflow>> workflows;

	private final Executor executor;

	/**
	 * Constructor
	 * 
	 * @param coreConfigurationLoader
	 * @throws ConfigurationException
	 */
	@Inject
	public ActionService(ICoreConfigurationLoader coreConfigurationLoader, RTFMProperties properties)
			throws ConfigurationException {
		logger.debug("Init ActionService");
		workflows = coreConfigurationLoader.getWorkflows();
		Integer nbThread = properties.getInteger("job.threads");
		if (nbThread == null) {
			nbThread = DEFAULT_NBTHREADS;
		}
		logger.debug("Using threads for job: " + nbThread);
		executor = Executors.newFixedThreadPool(nbThread);
	}

	/**
	 * Creates a Job to be submitted
	 * 
	 * @param workflowIdentifier
	 * @param musicFiles
	 * @return
	 * @throws ActionException
	 */
	public IJob createJob(String workflowIdentifier, List<IMusicFile> musicFiles) throws ActionException {
		if (!workflows.containsKey(workflowIdentifier)) {
			throw new ActionException("Unknown workflow: " + workflowIdentifier);
		}
		return new Job(workflows.get(workflowIdentifier), musicFiles, executor);
	}

	/**
	 * Get All Workflow Ids
	 * 
	 * @return
	 */
	public Set<String> getAllActions() {
		return workflows.keySet();
	}

	/**
	 * Get all compatible workflows for a given filetype
	 * 
	 * @param fileType
	 * @return
	 */
	public List<String> getWorkflowIdentifiersForType(FileType fileType) {
		List<String> out = new ArrayList<String>();
		for (Workflow workflow : workflows) {
			if (workflow.supportType(fileType)) {
				out.add(workflows.getIdentifier().getId(workflow));
			}
		}
		return out;
	}
}
