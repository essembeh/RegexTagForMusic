package org.essembeh.rtfm.core.configuration.io;

import java.util.List;

import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.utils.version.IReader;
import org.essembeh.rtfm.core.workflow.TaskDescription;
import org.essembeh.rtfm.core.workflow.Workflow;

public interface ICoreConfigurationProvider extends IReader {

	List<FileHandler> getFileHandlers();

	List<Workflow> getWorkflows();

	List<TaskDescription> getTasks();
}
