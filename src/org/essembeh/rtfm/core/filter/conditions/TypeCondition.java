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
package org.essembeh.rtfm.core.filter.conditions;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class TypeCondition implements IFilterCondition {

	private String[] validTypes;

	public TypeCondition(String... validTypes) {
		this.validTypes = validTypes;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		for (int i = 0; i < validTypes.length; i++) {
			if (musicFile.getType().equals(FileType.getFiletype(validTypes[i]))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "TypeCondition [validTypes=" + StringUtils.join(validTypes, ", ") + "]";
	}

}
