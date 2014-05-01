package org.essembeh.rtfm.core.filehandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.essembeh.rtfm.core.filehandler.attribute.IAttributeGenerator;
import org.essembeh.rtfm.core.filehandler.attribute.SimpleAttributeGenerator;
import org.essembeh.rtfm.core.utils.id.Identified;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.AttributesHelper;

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
		attributes.add(new SimpleAttributeGenerator(AttributesHelper.FILEHANDLER_KEY, id));
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
