package org.essembeh.rtfm.core.actions.listener;

import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public interface IJobListener {

	void start(Workflow workflow);

	void process(Workflow workflow, IMusicFile musicFile);

	void succeeded(Workflow workflow, IMusicFile musicFile);

	void error(Workflow workflow, IMusicFile musicFile, ActionException e);

	void end(Workflow workflow);

}
