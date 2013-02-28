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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public class RegexAttribute extends AbstractNamedAttribute {

	static protected Logger logger = Logger.getLogger(RegexAttribute.class);

	private final Pattern pattern;
	private final int group;
	private final boolean optional;

	public RegexAttribute(String name, Pattern pattern, int group, boolean optional) {
		super(name);
		this.pattern = pattern;
		this.group = group;
		this.optional = optional;
	}

	@Override
	public String getValue(VirtualFile file) throws DynamicAttributeException {
		String out = null;
		String virtualPath = file.getVirtualPath();
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
			throw new DynamicAttributeException(file, getName());
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
