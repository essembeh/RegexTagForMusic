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
import org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.XFile;
import org.essembeh.rtfm.core.library.io.ILibraryProvider;
import org.essembeh.rtfm.core.properties.RTFMProperties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class CoreConfigurationServices extends CoreConfiguration implements IWorkflowService, IXFileService {

	/**
	 * Attributes
	 */
	private final static Logger logger = Logger.getLogger(CoreConfigurationServices.class);
	private final static Integer DEFAULT_NBTHREADS = 4;
	private final Executor executor;

	/**
	 * 
	 * @param reader
	 * @param properties
	 */
	@Inject
	public CoreConfigurationServices(@Named("CoreConfigurationReader") ICoreConfigurationProvider configurationReader, RTFMProperties properties) {
		super(configurationReader);
		Integer nbThread = properties.getWithDefault("job.threads", DEFAULT_NBTHREADS);
		logger.debug("Using threads for job: " + nbThread);
		executor = Executors.newFixedThreadPool(nbThread);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.IXFileService#createXFile(org.essembeh.rtfm.core.library.file.VirtualFile,
	 * org.essembeh.rtfm.core.library.io.ILibraryProvider)
	 */
	public IXFile createXFile(VirtualFile file, ILibraryProvider provider) {
		XFile out = null;
		for (FileHandler fileHandler : getFileHandlers()) {
			if (fileHandler.getConditions().isTrue(file)) {
				logger.debug("Found filhandler: " + fileHandler + ", for file: " + file);
				out = new XFile(fileHandler.getType(), file);
				out.getAttributeList().addAll(fileHandler.getAttributesForFile(out.getFile()));
				if (provider != null && provider.fileExists(file)) {
					logger.debug("Updating attributes for file: " + file);
					out.getAttributeList().addAll(provider.getAttributesOfFile(file));
				}
				break;
			}
		}
		if (out == null) {
			logger.info("Cannot find filehandler for file: " + file);
		}
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.IExecutionEnvironment#createJob(org.essembeh.rtfm.core.actions.IWorkflowIdentifier, java.util.List)
	 */
	@Override
	public IJob createJob(IWorkflowIdentifier workflowIdentifier, List<IXFile> musicFiles) throws ActionException {
		Workflow theWorkflow = null;
		for (Workflow workflow : getWorkflows()) {
			if (workflow.getIdentifier().equals(workflowIdentifier)) {
				theWorkflow = workflow;
				break;
			}
		}
		if (theWorkflow == null) {
			throw new ActionException("Unknown workflow: " + workflowIdentifier);
		}
		return new Job(theWorkflow, musicFiles, executor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.IExecutionEnvironment#getAllWorkflows()
	 */
	@Override
	public List<IWorkflowIdentifier> getAllWorkflows() {
		List<IWorkflowIdentifier> out = new ArrayList<IWorkflowIdentifier>();
		for (Workflow workflow : getWorkflows()) {
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
		// TODO
		Collections.sort(out);
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.IXFileService#getDeclaredFileTypes()
	 */
	@Override
	public List<FileType> getDeclaredFileTypes() {
		List<FileType> out = new ArrayList<FileType>();
		for (FileHandler fileHandler : getFileHandlers()) {
			out.add(fileHandler.getType());
		}
		return out;
	}
}
