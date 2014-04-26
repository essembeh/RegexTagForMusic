package org.essembeh.rtfm.exec.tasks;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.exec.utils.CommonExecutable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ThrowException extends CommonExecutable implements IExecutable {

	@Override
	public int execute0(IResource resource) throws ExecutionException {
		throw new ExecutionException(StringUtils.defaultIfBlank(StringUtils.join(getPropertiesByKey("message"), " "),
				"No message"));
	}
}
