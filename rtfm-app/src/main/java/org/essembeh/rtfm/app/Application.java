package org.essembeh.rtfm.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.config.ConfigurationReader;
import org.essembeh.rtfm.app.config.RtfmProperties;
import org.essembeh.rtfm.app.exception.MissingTaskException;
import org.essembeh.rtfm.app.exception.TaskInstanciationException;
import org.essembeh.rtfm.app.filehandler.FileHandler;
import org.essembeh.rtfm.app.filehandler.FileHandlerScannerExtension;
import org.essembeh.rtfm.app.filehandler.FileHandlerScannerExtension.AttributeErrorOption;
import org.essembeh.rtfm.app.utils.DateUtils;
import org.essembeh.rtfm.app.utils.JobUtils;
import org.essembeh.rtfm.app.workflow.IJob;
import org.essembeh.rtfm.app.workflow.IWorkflow;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.app.workflow.impl.DefaultJobProgressMonitor;
import org.essembeh.rtfm.app.workflow.impl.Workflow;
import org.essembeh.rtfm.app.workflow.impl.WorkflowManager;
import org.essembeh.rtfm.fs.condition.impl.AttributeValueEquals;
import org.essembeh.rtfm.fs.content.Attributes;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.fs.io.NoHiddenFilesScannerExtension;
import org.essembeh.rtfm.fs.io.ProjectReaderScannerExtension;
import org.essembeh.rtfm.fs.io.ProjectScanner;
import org.essembeh.rtfm.fs.io.ProjectWriter;
import org.essembeh.rtfm.model.JaxbReader;

import com.google.inject.Inject;

public class Application {

	// Log4j logger
	private final Logger logger = Logger.getLogger(Application.class);

	private IProject project;
	private final WorkflowManager workflowManager;
	private final RtfmProperties properties;
	private final List<FileHandler> fileHandlers;

	@Inject
	public Application(WorkflowManager workflowManager, RtfmProperties properties) {
		this.properties = properties;
		this.workflowManager = workflowManager;
		fileHandlers = new ArrayList<>();
		project = null;
	}

	public void loadConfiguration(InputStream configurationStream) throws JAXBException, MissingTaskException {
		logger.debug("Loading configuration from stream");
		// Clear
		workflowManager.clear();
		fileHandlers.clear();
		properties.clear();
		// Load
		ConfigurationReader configurationReader = new ConfigurationReader(
				JaxbReader.readConfiguration(configurationStream));
		List<Workflow> workflowList = configurationReader.readWorkflows();
		List<FileHandler> fileHandlerList = configurationReader.readFileHandlers();
		// Update
		fileHandlers.addAll(fileHandlerList);
		workflowManager.addWorkflows(workflowList);
		properties.putAll(configurationReader.readProperties());
	}

	private ProjectScanner getConfiguredProjectScanner() {
		ProjectScanner out = new ProjectScanner(DateUtils.now());
		// Hidden files
		if (properties.ignoreHiddenResources()) {
			out.addExtension(new NoHiddenFilesScannerExtension());
		}
		// File handlers
		FileHandlerScannerExtension extension = new FileHandlerScannerExtension(fileHandlers);
		extension.setAttributeErrorOption(AttributeErrorOption.UPDATE_ERROR_ATTRIBUTE);
		out.addExtension(extension);
		return out;
	}

	private void executeAutomaticWorkflows(IProject project, String scanDate) {
		// Search new files
		List<IResource> resources = project.getRootFolder().getFilteredResources(
				new AttributeValueEquals(Attributes.DATE_KEY, scanDate));

		for (IWorkflow workflow : workflowManager.getWorkflows()) {
			if (workflow.isAuto()) {
				try {
					IJob job = workflowManager.createJob(workflow, resources);
					JobUtils.synExec(job, new DefaultJobProgressMonitor());
					job.updateErrorResources();
				} catch (TaskInstanciationException | InterruptedException e) {
					logger.error("Cannot run auto workflow: " + workflow, e);
				}
			}
		}
	}

	public IProject loadProject(File libraryFile) throws FileSystemException, FileNotFoundException, JAXBException {
		project = null;
		ProjectScanner scanner = getConfiguredProjectScanner();
		// Handle existing attributes
		ProjectReaderScannerExtension readerExtension = new ProjectReaderScannerExtension(
				JaxbReader.readProject(new FileInputStream(libraryFile)));
		scanner.addExtension(readerExtension);
		// Scan root folder
		project = scanner.scanFolder(readerExtension.getRootFolder());
		// Run auto workflows
		executeAutomaticWorkflows(project, scanner.getDate());
		return project;
	}

	public IProject scanFolder(File rootFolder) throws FileSystemException {
		project = null;
		ProjectScanner scanner = getConfiguredProjectScanner();
		// Scan root folder
		project = scanner.scanFolder(rootFolder);
		// Run auto workflows
		executeAutomaticWorkflows(project, scanner.getDate());
		return project;
	}

	public void saveProject(File libraryFile) throws FileNotFoundException, JAXBException {
		ProjectWriter projectWriter = new ProjectWriter();
		projectWriter.write(project, new FileOutputStream(libraryFile));
	}

	public IProject getProject() {
		return project;
	}

	public IWorkflowManager getWorkflowManager() {
		return workflowManager;
	}

	public RtfmProperties getProperties() {
		return properties;
	}
}
