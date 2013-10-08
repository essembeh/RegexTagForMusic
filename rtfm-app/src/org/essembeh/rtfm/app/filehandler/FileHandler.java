package org.essembeh.rtfm.app.filehandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.essembeh.rtfm.app.filehandler.attribute.IAttributeGenerator;
import org.essembeh.rtfm.app.filehandler.attribute.SimpleAttributeGenerator;
import org.essembeh.rtfm.app.utils.id.Identified;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.Attributes;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class FileHandler extends Identified {

	/**
	 * Attributes
	 */
	private ICondition condition;
	private final List<IAttributeGenerator> attributes;

	public FileHandler(String id) {
		super(id);
		condition = null;
		attributes = new ArrayList<IAttributeGenerator>();
		attributes.add(new SimpleAttributeGenerator(Attributes.FILEHANDLER_KEY, id));
	}

	public void addAttribute(IAttributeGenerator attribute) {
		this.attributes.add(attribute);
	}

	public boolean matches(IResource resource) {
		return condition == null || condition.isTrue(resource);
	}

	public void setCondition(ICondition condition) {
		this.condition = condition;
	}

	public List<IAttributeGenerator> getAttributes() {
		return attributes;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
