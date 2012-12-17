package org.essembeh.rtfm.core.configuration.io;

import java.util.List;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.version.MultiReader;

import com.google.inject.Inject;

public class MultiCoreConfigurationReader extends MultiReader<ICoreConfigurationProvider> implements ICoreConfigurationProvider {

	/**
	 * 
	 */
	@Inject
	public MultiCoreConfigurationReader(CoreConfigurationReaderV2 version2, CoreConfigurationReaderV1 version1) {
		addReader(version2);
		addReader(version1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider#getFileHandlers()
	 */
	@Override
	public List<FileHandler> getFileHandlers() {
		return getLastReader().getFileHandlers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider#getWorkflows()
	 */
	@Override
	public List<Workflow> getWorkflows() {
		return getLastReader().getWorkflows();
	}
}
