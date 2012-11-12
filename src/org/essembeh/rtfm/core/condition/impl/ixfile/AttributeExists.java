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
package org.essembeh.rtfm.core.condition.impl.ixfile;

import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.library.file.IXFile;

public class AttributeExists implements ICondition<IXFile> {

	/**
	 * 
	 */
	private final String attributeName;
	private final boolean exists;

	/**
	 * 
	 * @param attributeName
	 * @param exists
	 */
	public AttributeExists(String attributeName, boolean exists) {
		this.attributeName = attributeName;
		this.exists = exists;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(IXFile input) {
		return !(input.getAttributeList().containsKey(attributeName) ^ exists);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + " [attributeName:" + attributeName + ", exists:" + exists + "]";
	}

}
