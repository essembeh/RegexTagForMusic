package org.essembeh.rtfm.core.filehandler.dynamic;

import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public class SimpleAttribute extends AbstractNamedAttribute {

	private final String value;

	public SimpleAttribute(String name, String value) {
		super(name);
		this.value = value;
	}

	@Override
	public String getValue(VirtualFile file) throws DynamicAttributeException {
		return value;
	}

}
