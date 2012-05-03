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
package org.essembeh.rtfm.core.library.filter;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.library.filter.conditions.AttributeValueCondition;
import org.essembeh.rtfm.core.library.filter.conditions.TypeCondition;

public class CommonFilters {

	public static Filter noFilter() {
		return new Filter();
	}

	public static Filter filterOnAttribute(String attributeName, String expectedValue) {
		Filter filter = new Filter();
		filter.addCondition(new AttributeValueCondition(attributeName, Pattern.compile(expectedValue)));
		return filter;
	}

	public static Filter filterOnType(String expectedValue) {
		Filter filter = new Filter();
		filter.addCondition(new TypeCondition(new String[] { expectedValue }));
		return filter;
	}
}
