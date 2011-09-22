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
package org.essembeh.rtfm.core.tag.fields;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.conf.RTFMProperties;
import org.essembeh.rtfm.core.exception.TagNotFoundException;
import org.essembeh.rtfm.interfaces.ITagField;

/**
 * 
 * @author seb
 * 
 */
public class RegexField implements ITagField {

	/**
	 * 
	 */
	static protected Logger logger = Logger.getLogger(RegexField.class);

	/**
	 * 
	 */
	protected Pattern pattern;

	/**
	 * 
	 */
	protected int group;

	/**
	 * 
	 * @param pattern
	 */
	public RegexField(Pattern pattern) {
		this(pattern, 1);
	}

	/**
	 * 
	 * @param pattern
	 * @param group
	 */
	public RegexField(Pattern pattern, int group) {
		this.pattern = pattern;
		this.group = group;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.tag.ITagField#getValue(java.lang.String)
	 */
	@Override
	public String getValue(String path) throws TagNotFoundException {
		String value = null;
		Matcher matcher = this.pattern.matcher(path);
		if (matcher.matches() && (matcher.groupCount() >= this.group)) {
			value = matcher.group(this.group);
			logger.debug("Regex : " + this.pattern.pattern() + " matches path: " + path + ", result: " + value);
		} else {
			throw new TagNotFoundException("Cannot match: " + this.pattern.pattern() + " on path: " + path);
		}
		if ("true".equalsIgnoreCase(RTFMProperties.getProperty("tag.regexfield.replace_underscore_by_space"))) {
			if (value != null) {
				value = value.replace("_", " ");
			}
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegexField";
	}

}
