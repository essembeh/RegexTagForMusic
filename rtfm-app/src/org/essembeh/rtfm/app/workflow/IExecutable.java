package org.essembeh.rtfm.app.workflow;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.utils.IConfigurable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IExecutable extends IConfigurable {

	int execute(IResource resource) throws ExecutionException;

}
