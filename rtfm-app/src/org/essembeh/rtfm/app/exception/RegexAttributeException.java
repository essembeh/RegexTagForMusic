package org.essembeh.rtfm.app.exception;

import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.exception.CommonException;

public class RegexAttributeException extends CommonException {
	private static final long serialVersionUID = 6208814599696773565L;

	public RegexAttributeException(IResource resource, String attributeName) {
		super(String.format("Cannot create attribute '%s'", attributeName));
	}

}