package org.essembeh.rtfm.core.workflow;

import org.essembeh.rtfm.core.exception.ExecutionException;
import org.essembeh.rtfm.core.utils.IConfigurable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IExecutable extends IConfigurable {

	int execute(IResource resource) throws ExecutionException;

}
