package org.essembeh.rtfm.core.filehandler.attribute;

import org.essembeh.rtfm.core.exception.AttributeException;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IAttributeGenerator {

	String getName();

	String getValue(IResource resource) throws AttributeException;
}
