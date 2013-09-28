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
package org.essembeh.rtfm.fs.condition.impl;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class AttributeExists implements ICondition {

	private final String attributeName;
	private final boolean exists;

	public AttributeExists(String attributeName, boolean exists) {
		this.attributeName = attributeName;
		this.exists = exists;
	}

	@Override
	public boolean isTrue(IResource resource) {
		return !(resource.getAttributes().contains(attributeName) ^ exists);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
