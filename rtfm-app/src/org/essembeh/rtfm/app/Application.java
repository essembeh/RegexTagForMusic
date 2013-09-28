package org.essembeh.rtfm.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.config.ConfigurationReader;
import org.essembeh.rtfm.app.exception.UnknownTaskException;
import org.essembeh.rtfm.app.filehandler.FileHandler;
import org.essembeh.rtfm.app.filehandler.FileHandlerScannerExtension;
import org.essembeh.rtfm.app.filehandler.FileHandlerScannerExtension.AttributeErrorOption;
import org.essembeh.rtfm.app.filehandler.FileHandlerScannerExtension.AttributeExistsOption;
import org.essembeh.rtfm.app.workflow.IWorkflowManager;
import org.essembeh.rtfm.app.workflow.impl.Workflow;
import org.essembeh.rtfm.app.workflow.impl.WorkflowManager;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.exception.FileSystemException;
import org.essembeh.rtfm.fs.io.ProjectReaderScannerExtension;
import org.essembeh.rtfm.fs.io.ProjectScanner;
import org.essembeh.rtfm.fs.io.ProjectWriter;
import org.essembeh.rtfm.model.gen.JaxbReader;

public class Application {

	// Log4j logger
	private final Logger logger = Logger.getLogger(Application.class);

	private IProject project;
	private final WorkflowManager workflowManager;
	private final Map<String, String> properties;
	private final List<FileHandler> fileHandlers;

	public Application() {
		project = null;
		workflowManager = new WorkflowManager();
		properties = new HashMap<>();
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

	public IProject loadProject(File libraryFile) throws FileSystemException, FileNotFoundException, JAXBException {
		project = null;
		ProjectScanner scanner = new ProjectScanner();
		// Handle filehandlers
		FileHandlerScannerExtension fileHandlerScannerExtension = new FileHandlerScannerExtension(
				AttributeErrorOption.UPDATE_ERROR_ATTRIBUTE, AttributeExistsOption.UPDATE, fileHandlers);
		scanner.addExtension(fileHandlerScannerExtension);
		// Handle existing attributes
		ProjectReaderScannerExtension projectReaderScannerExtension = new ProjectReaderScannerExtension(
				JaxbReader.readProject(new FileInputStream(libraryFile)));
		scanner.addExtension(projectReaderScannerExtension);
		// Scan root folder
		return (project = scanner.scanFolder(projectReaderScannerExtension.getRootFolder()));
	}

	public IProject scanFolder(File rootFolder) throws FileSystemException {
		project = null;
		ProjectScanner scanner = new ProjectScanner();
		// Handle filehandlers
		FileHandlerScannerExtension fileHandlerScannerExtension = new FileHandlerScannerExtension(
				AttributeErrorOption.UPDATE_ERROR_ATTRIBUTE, AttributeExistsOption.UPDATE, fileHandlers);
		scanner.addExtension(fileHandlerScannerExtension);
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

	public String getProperty(String key) {
		return getProperty(key, null);
	}

	public String getProperty(String key, String defautlValue) {
		String out = properties.get(key);
		return out == null ? defautlValue : out;
	}
}
