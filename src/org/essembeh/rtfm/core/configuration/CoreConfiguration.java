package org.essembeh.rtfm.core.configuration;

import java.io.InputStream;
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
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.properties.RTFMProperties;
import org.essembeh.rtfm.core.utils.identifiers.WorkflowIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

import com.google.inject.Inject;

public class CoreConfiguration implements IExecutionEnvironment {

	/**
	 * Attributes
	 */
	private final static Logger LOGGER = Logger.getLogger(CoreConfiguration.class);
	private final static Integer DEFAULT_NBTHREADS = 4;
	private final ICoreConfigurationLoader configurationLoader;
	private final List<FileHandler> fileHandlers;
	private final IdList<Workflow, Identifier<Workflow>> workflows;
	private final Executor executor;

	/**
	 * 
	 * @param configurationLoader
	 * @param configurationHelper
	 * @param properties
	 */
	@Inject
	public CoreConfiguration(ICoreConfigurationLoader configurationLoader, RTFMProperties properties) {
		this.configurationLoader = configurationLoader;
		this.fileHandlers = new ArrayList<FileHandler>();
		this.workflows = new IdList<Workflow, Identifier<Workflow>>(new WorkflowIdentifier());
		Integer nbThread = properties.getWithDefault("job.threads", DEFAULT_NBTHREADS);
		LOGGER.debug("Using threads for job: " + nbThread);
		executor = Executors.newFixedThreadPool(nbThread);
	}

	/**
	 * 
	 * @param input
	 * @throws ConfigurationException
	 */
	public void load(InputStream input) throws ConfigurationException {
		// Clean
		this.fileHandlers.clear();
		this.workflows.clear();
		// Load
		configurationLoader.loadConfiguration(input);
		fileHandlers.addAll(configurationLoader.getFileHandlers());
		workflows.addAll(configurationLoader.getWorkflows());
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws DynamicAttributeException
	 */
	public IMusicFile createMusicFile(VirtualFile file) throws DynamicAttributeException {
		MusicFile musicFile = null;
		for (FileHandler fileHandler : fileHandlers) {
			if (fileHandler.canHandle(file)) {
				musicFile = new MusicFile(fileHandler.getType(), file);
				musicFile.getAttributeList().addAll(fileHandler.getAttributesForFile(musicFile.getFile()));
				break;
			}
		}
		if (musicFile == null) {
			LOGGER.info("Cannot find filehandler for file: " + file);
		}
		return musicFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.IExecutionEnvironment#createJob(org.essembeh.rtfm.core.actions.IWorkflowIdentifier, java.util.List)
	 */
	@Override
	public IJob createJob(IWorkflowIdentifier workflowIdentifier, List<IMusicFile> musicFiles) throws ActionException {
		if (!workflows.containsKey(workflowIdentifier.getIdentifier())) {
			throw new ActionException("Unknown workflow: " + workflowIdentifier);
		}
		return new Job(workflows.get(workflowIdentifier.getIdentifier()), musicFiles, executor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.IExecutionEnvironment#getAllWorkflows()
	 */
	@Override
	public List<IWorkflowIdentifier> getAllWorkflows() {
		List<IWorkflowIdentifier> out = new ArrayList<IWorkflowIdentifier>();
		for (Workflow workflow : workflows) {
			out.add(workflow);
		}
		Collections.sort(out);
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.IExecutionEnvironment#getWorkflowsForType(org.essembeh.rtfm.core.library.file.FileType)
	 */
	@Override
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
