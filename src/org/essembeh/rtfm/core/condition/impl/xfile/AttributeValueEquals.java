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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.library.file.IXFile;

import com.google.common.base.Objects;

public class AttributeValueEquals<T extends IXFile> implements ICondition<T> {

	/**
	 * 
	 */
	private final String attributeName;
	private final String expectedValue;

	/**
	 * 
	 * @param attributeName
	 * @param expectedValue
	 */
	public AttributeValueEquals(String attributeName, String expectedValue) {
		this.attributeName = attributeName;
		this.expectedValue = expectedValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(T input) {
		return input.getAttributes().contains(attributeName)
				&& Objects.equal(expectedValue, input.getAttributes().getAttributeValue(attributeName));
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
