package org.essembeh.rtfm.core.filehandler.dynamic;

public abstract class AbstractNamedAttribute implements IDynamicAttribute {

	private final String name;

	public AbstractNamedAttribute(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
