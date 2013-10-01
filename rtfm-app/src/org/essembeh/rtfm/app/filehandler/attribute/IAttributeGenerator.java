package org.essembeh.rtfm.app.filehandler.attribute;

import org.essembeh.rtfm.app.exception.AttributeException;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IAttributeGenerator {

	String getName();

	String getValue(IResource resource) throws AttributeException;
}
