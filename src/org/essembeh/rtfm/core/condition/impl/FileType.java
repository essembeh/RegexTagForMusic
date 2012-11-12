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
package org.essembeh.rtfm.core.condition.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.library.file.IXFile;

public class FileType implements ICondition<IXFile> {

	/**
	 * 
	 */
	private List<String> validTypes;

	/**
	 * 
	 * @param validTypes
	 */
	public FileType(String... validTypes) {
		this.validTypes = Arrays.asList(validTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(IXFile input) {
		return validTypes.contains(input.getType().getIdentifier());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + "  [validTypes=" + StringUtils.join(validTypes, ", ") + "]";
	}

}
