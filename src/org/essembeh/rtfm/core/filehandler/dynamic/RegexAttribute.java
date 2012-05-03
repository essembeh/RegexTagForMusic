/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
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
