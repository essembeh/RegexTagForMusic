package org.essembeh.rtfm.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.config.ConfigurationReader;
import org.essembeh.rtfm.app.config.RtfmProperties;
import org.essembeh.rtfm.app.exception.UnknownTaskException;
import org.essembeh.rtfm.app.filehandler.FileHandler;
import org.essembeh.rtfm.app.filehandler.FileHandlerScannerExtension;
import org.essembeh.rtfm.app.filehandler.FileHandlerScannerExtension.AttributeErrorOption;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.app.workflow.impl.Workflow;
import org.essembeh.rtfm.app.workflow.impl.WorkflowManager;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.fs.io.NoHiddenFilesScannerExtension;
import org.essembeh.rtfm.fs.io.ProjectReaderScannerExtension;
import org.essembeh.rtfm.fs.io.ProjectScanner;
import org.essembeh.rtfm.fs.io.ProjectWriter;
import org.essembeh.rtfm.model.JaxbReader;

public class Application {

	// Log4j logger
	private final Logger logger = Logger.getLogger(Application.class);

	private IProject project;
	private final WorkflowManager workflowManager;
	private final RtfmProperties properties;
	private final List<FileHandler> fileHandlers;

	public Application() {
		project = null;
		workflowManager = new WorkflowManager();
		properties = new RtfmProperties();
		fileHandlers = new ArrayList<>();
	}

	public void loadConfiguration(File configurationFile) throws FileNotFoundException, JAXBException,
			UnknownTaskException {
		logger.info("Loading configuration: " + configurationFile.getAbsolutePath());
		// Clear
		workflowManager.clear();
		fileHandlers.clear();
		properties.clear();
		// Load
		ConfigurationReader configurationReader = new ConfigurationReader(
				JaxbReader.readConfiguration(new FileInputStream(configurationFile)));
		List<Workflow> workflowList = configurationReader.readWorkflows();
		List<FileHandler> fileHandlerList = configurationReader.readFileHandlers();
		// Update
		fileHandlers.addAll(fileHandlerList);
		workflowManager.addWorkflows(workflowList);
		properties.putAll(configurationReader.readProperties());
	}

	private ProjectScanner getConfiguredProjectScanner() {
		ProjectScanner out = new ProjectScanner();
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

	public IProject loadProject(File libraryFile) throws FileSystemException, FileNotFoundException, JAXBException {
		project = null;
		ProjectScanner scanner = getConfiguredProjectScanner();
		// Handle existing attributes
		ProjectReaderScannerExtension readerExtension = new ProjectReaderScannerExtension(
				JaxbReader.readProject(new FileInputStream(libraryFile)));
		scanner.addExtension(readerExtension);
		// Scan root folder
		return (project = scanner.scanFolder(readerExtension.getRootFolder()));
	}

	public IProject scanFolder(File rootFolder) throws FileSystemException {
		project = null;
		ProjectScanner scanner = getConfiguredProjectScanner();
		// Scan root folder
		return (project = scanner.scanFolder(rootFolder));
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
