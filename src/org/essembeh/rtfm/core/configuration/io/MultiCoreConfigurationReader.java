package org.essembeh.rtfm.core.configuration.io;

import java.util.List;

import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.version.MultiReader;
import org.essembeh.rtfm.core.workflow.TaskDescription;
import org.essembeh.rtfm.core.workflow.Workflow;

import com.google.inject.Inject;

public class MultiCoreConfigurationReader extends MultiReader<ICoreConfigurationProvider> implements
		ICoreConfigurationProvider {

	@Inject
	public MultiCoreConfigurationReader(CoreConfigurationReader version2, CoreConfigurationReader version1) {
		addReader(version2);
		addReader(version1);
	}

	@Override
	public List<FileHandler> getFileHandlers() {
		return getLastReader().getFileHandlers();
	}

	@Override
	public List<Workflow> getWorkflows() {
		return getLastReader().getWorkflows();
	}

	@Override
	public List<TaskDescription> getTasks() {
		return getLastReader().getTasks();
	}
}
