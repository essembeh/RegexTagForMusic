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
package org.essembeh.rtfm.core.library.filter.conditions;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;

public class AttributeValueCondition implements IFilterCondition {

	String attributeName;

	Pattern regexOnValue;

	public AttributeValueCondition(String attributeName, Pattern regexOnValue) {
		this.attributeName = attributeName;
		this.regexOnValue = regexOnValue;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		boolean condition = false;
		Attribute attribute = musicFile.getAttributeList().get(attributeName);
		if (attribute != null) {
			String value = attribute.getValue();
			condition = regexOnValue.matcher(value).matches();
		}
		return condition;
	}

	@Override
	public String toString() {
		return "AttributeValueCondition [attributeName=" + attributeName + ", regexOnValue=" + regexOnValue.pattern()
				+ "]";
	}

}
