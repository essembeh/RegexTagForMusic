package org.essembeh.rtfm.core.configuration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.version.ILoadable;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;
import org.essembeh.rtfm.core.workflow.TaskDescription;
import org.essembeh.rtfm.core.workflow.Workflow;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class CoreConfiguration implements ILoadable {

	/**
	 * Attributes
	 */
	private final ICoreConfigurationProvider configurationReader;
	private final List<FileHandler> fileHandlers;
	private final List<Workflow> workflows;
	private final List<TaskDescription> tasks;

	/**
	 * 
	 * @param configurationReader
	 */
	@Inject
	public CoreConfiguration(@Named("CoreConfigurationReader") ICoreConfigurationProvider configurationReader) {
		this.configurationReader = configurationReader;
		this.fileHandlers = new ArrayList<FileHandler>();
		this.workflows = new ArrayList<Workflow>();
		this.tasks = new ArrayList<TaskDescription>();
	}

	/**
	 * 
	 * @return
	 */
	public List<TaskDescription> getTaskDescriptions() {
		return Collections.unmodifiableList(tasks);
	}

	/**
	 * @return the fileHandlers
	 */
	public List<FileHandler> getFileHandlers() {
		return Collections.unmodifiableList(fileHandlers);
	}

	/**
	 * @return the workflows
	 */
	public List<Workflow> getWorkflows() {
		return workflows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.ILoadable#load(java.io.InputStream)
	 */
	@Override
	public void load(InputStream inputStream) throws ReaderException {
		// Clear
		fileHandlers.clear();
		workflows.clear();
		tasks.clear();
		// Read
		configurationReader.read(inputStream);
		// Update
		fileHandlers.addAll(configurationReader.getFileHandlers());
		workflows.addAll(configurationReader.getWorkflows());
		tasks.addAll(configurationReader.getTasks());
	}
}
