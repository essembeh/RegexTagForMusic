/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core.configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.IJob;
import org.essembeh.rtfm.core.actions.IWorkflowIdentifier;
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
	public ActionService(ICoreConfigurationLoader coreConfigurationLoader, RTFMProperties properties) throws ConfigurationException {
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
	public IJob createJob(IWorkflowIdentifier workflowIdentifier, List<IMusicFile> musicFiles) throws ActionException {
		if (!workflows.containsKey(workflowIdentifier.getIdentifier())) {
			throw new ActionException("Unknown workflow: " + workflowIdentifier);
		}
		return new Job(workflows.get(workflowIdentifier.getIdentifier()), musicFiles, executor);
	}

	/**
	 * Get All Workflow Ids
	 * 
	 * @return
	 */
	public List<IWorkflowIdentifier> getAllWorkflows() {
		List<IWorkflowIdentifier> out = new ArrayList<IWorkflowIdentifier>();
		for (Workflow workflow : workflows) {
			out.add(workflow);
		}
		Collections.sort(out);
		return out;
	}

	/**
	 * Get all compatible workflows for a given filetype
	 * 
	 * @param fileType
	 * @return
	 */
	public List<IWorkflowIdentifier> getWorkflowsForType(FileType fileType) {
		List<IWorkflowIdentifier> out = new ArrayList<IWorkflowIdentifier>();
		for (Workflow workflow : workflows) {
			if (workflow.supportType(fileType)) {
				out.add(workflow);
			}
		}
		Collections.sort(out);
		return out;
	}
}
