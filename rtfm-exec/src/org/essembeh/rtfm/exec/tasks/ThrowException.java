package org.essembeh.rtfm.exec.tasks;

import org.essembeh.rtfm.app.exception.ExecutionException;
import org.essembeh.rtfm.app.utils.AbstractConfigurable;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ThrowException extends AbstractConfigurable implements IExecutable {

	@Override
	public void execute(IResource resource) throws ExecutionException {
		String message = getFirstProperty("message");
		throw new ExecutionException(message == null ? "No message" : message);
	}
}
