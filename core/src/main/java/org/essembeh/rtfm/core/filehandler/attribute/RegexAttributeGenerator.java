package org.essembeh.rtfm.core.filehandler.attribute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.AttributeException;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class RegexAttributeGenerator extends AbstractAttributeGenerator {

	static protected Logger logger = Logger.getLogger(RegexAttributeGenerator.class);

	private final Pattern pattern;
	private final int group;
	private final boolean optional;

	public RegexAttributeGenerator(String name, Pattern pattern, int group, boolean optional) {
		super(name);
		this.pattern = pattern;
		this.group = group;
		this.optional = optional;
	}

	@Override
	public String getValue(IResource resource) throws AttributeException {
		String out = null;
		String virtualPath = resource.getVirtualPath().toString();
		Matcher matcher = this.pattern.matcher(virtualPath);
		if (matcher.matches() && (matcher.groupCount() >= this.group)) {
			out = matcher.group(this.group);
		}

		if (out != null) {
			logger.debug("Match: " + toString() + ", on: " + virtualPath + ", result: " + out);
		} else if (this.optional) {
			logger.debug("Optional regex attribute: " + toString() + ", did not match on:" + virtualPath);
		} else {
			logger.error("Mandatory regex attribute: " + toString() + ", did not match on:" + virtualPath);
			throw new AttributeException(resource, getName());
		}
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
