package org.essembeh.rtfm.core.configuration;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.version.ILoadable;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;

public class CoreConfiguration implements ILoadable {

	/**
	 * Attributes
	 */
	private final ICoreConfigurationProvider configurationReader;
	private final List<FileHandler> fileHandlers;
	private final List<Workflow> workflows;

	/**
	 * 
	 * @param configurationReader
	 */
	public CoreConfiguration(ICoreConfigurationProvider configurationReader) {
		this.configurationReader = configurationReader;
		this.fileHandlers = new ArrayList<FileHandler>();
		this.workflows = new ArrayList<Workflow>();
	}

	/**
	 * @return the fileHandlers
	 */
	public List<FileHandler> getFileHandlers() {
		return fileHandlers;
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
		// Read
		configurationReader.read(inputStream);
		// Update
		fileHandlers.addAll(configurationReader.getFileHandlers());
		workflows.addAll(configurationReader.getWorkflows());
	}
}
