package org.essembeh.rtfm.app.filehandler.attribute;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class SimpleAttributeGenerator extends AbstractAttributeGenerator {

	private final String value;

	public SimpleAttributeGenerator(String name, String value) {
		super(name);
		this.value = value;
	}

	@Override
	public String getValue(IResource resource) {
		return value;
	}

}
