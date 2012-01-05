package org.essembeh.rtfm.core.attributes.dynamic;

import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public interface DynamicAttribute {

	String getName();

	Attribute createAttribute(VirtualFile file) throws DynamicAttributeException;
}
