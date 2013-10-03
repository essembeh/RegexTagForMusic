package org.essembeh.rtfm.app.exception;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class UnsupportedResource extends ExecutionException {

	private static final long serialVersionUID = -6785594426390020732L;

	public UnsupportedResource(IResource resource) {
		super("Resource is not supported");
	}
}
