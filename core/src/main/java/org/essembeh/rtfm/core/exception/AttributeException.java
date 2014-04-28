package org.essembeh.rtfm.core.exception;

import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.CommonException;

public class AttributeException extends CommonException {
	private static final long serialVersionUID = 6208814599696773565L;

	public AttributeException(IResource resource, String attributeName) {
		super(String.format("Cannot create attribute '%s'", attributeName));
	}

}
