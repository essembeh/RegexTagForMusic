package org.essembeh.rtfm.core.filehandler.dynamic;

import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;

public interface IDynamicAttribute {

	String getName();

	Attribute createAttribute(VirtualFile file) throws DynamicAttributeException;
}
