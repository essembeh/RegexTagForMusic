package org.essembeh.rtfm.core.workflow;

import org.essembeh.rtfm.core.exception.WorkflowException;
import org.essembeh.rtfm.core.library.file.IXFile;

public interface IJobProgressMonitor {

	void start();

	void end();

	void error(IXFile file, WorkflowException e);

	void succeeded(IXFile file);

	void process(IXFile file);

}
