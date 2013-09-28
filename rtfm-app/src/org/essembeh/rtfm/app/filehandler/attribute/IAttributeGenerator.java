package org.essembeh.rtfm.app.filehandler.attribute;

import org.essembeh.rtfm.app.exception.RegexAttributeException;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IAttributeGenerator {

	String getName();

	String getValue(IResource resource) throws RegexAttributeException;
}
