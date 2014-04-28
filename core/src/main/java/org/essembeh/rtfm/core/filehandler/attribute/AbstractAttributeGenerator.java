package org.essembeh.rtfm.core.filehandler.attribute;

public abstract class AbstractAttributeGenerator implements IAttributeGenerator {

	private final String name;

	public AbstractAttributeGenerator(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
