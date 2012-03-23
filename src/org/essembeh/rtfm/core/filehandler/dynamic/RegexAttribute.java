package org.essembeh.rtfm.core.filehandler.dynamic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;

public class RegexAttribute implements IDynamicAttribute {

	static protected Logger logger = Logger.getLogger(RegexAttribute.class);

	Pattern pattern;

	int group;

	boolean optional = false;

	Attribute attribute;

	public RegexAttribute(String name, boolean hidden, Pattern pattern, int group, boolean optional) {
		this.attribute = new Attribute(name, "", hidden);
		this.pattern = pattern;
		this.group = group;
		this.optional = optional;
	}

	@Override
	public Attribute createAttribute(VirtualFile file) throws DynamicAttributeException {
		Attribute theAttribute = null;
		String attributeValue = null;
		String virtualPath = file.getVirtualPath();
		Matcher matcher = this.pattern.matcher(virtualPath);
		if (matcher.matches() && (matcher.groupCount() >= this.group)) {
			attributeValue = matcher.group(this.group);
		}

		if (attributeValue != null) {
			logger.debug("Match: " + toString() + ", on: " + virtualPath + ", result: " + attributeValue);
			theAttribute = attribute.clone();
			theAttribute.setValue(attributeValue);
		} else if (this.optional) {
			logger.debug("Optional regex attribute: " + toString() + ", did not match on:" + virtualPath);
		} else {
			logger.error("Mandatory regex attribute: " + toString() + ", did not match on:" + virtualPath);
			throw new DynamicAttributeException(file, attribute.getName());
		}
		return theAttribute;
	}

	@Override
	public String toString() {
		return attribute.toString() + pattern.pattern() + " (" + group + ")" + (optional ? "?" : "");
	}

	@Override
	public String getName() {
		return attribute.getName();
	}
}
