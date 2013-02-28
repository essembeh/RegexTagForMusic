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
package org.essembeh.rtfm.core.condition.impl.xfile;

import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.library.file.IXFile;

public class AttributeValueRegex<T extends IXFile> implements ICondition<T> {

	/**
	 * Attributes
	 */
	private final String attributeName;
	private final Pattern regexOnValue;

	/**
	 * 
	 * @param attributeName
	 * @param regexOnValue
	 */
	public AttributeValueRegex(String attributeName, Pattern regexOnValue) {
		this.attributeName = attributeName;
		this.regexOnValue = regexOnValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(T input) {
		return input.getAttributes().contains(attributeName)
				&& regexOnValue.matcher(input.getAttributes().getAttributeValue(attributeName)).matches();
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
