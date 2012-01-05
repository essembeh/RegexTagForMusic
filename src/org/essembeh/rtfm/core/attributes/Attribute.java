package org.essembeh.rtfm.core.attributes;

public class Attribute implements Cloneable {

	String name;
	String value;
	boolean hidden;

	public Attribute(String name, String value, boolean hidden) {
		this.name = name;
		this.value = value;
		this.hidden = hidden;
	}

	public boolean isHidden() {
		return hidden;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public boolean setValue(String newValue) {
		this.value = newValue;
		return true;
	}

	@Override
	public Attribute clone() {
		return new Attribute(name, value, hidden);
	}

	@Override
	public String toString() {
		return (hidden ? "-" : "+") + name + "=" + value;
	}
}
