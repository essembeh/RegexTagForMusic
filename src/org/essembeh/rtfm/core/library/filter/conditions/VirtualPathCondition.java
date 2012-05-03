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

public class VirtualPathCondition implements IFilterCondition {

	Pattern regexOnPath;

	public VirtualPathCondition(Pattern regexOnPath) {
		this.regexOnPath = regexOnPath;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		return regexOnPath.matcher(musicFile.getVirtualPath()).matches();
	}

	@Override
	public String toString() {
		return "VirtualPathCondition [regexOnPath=" + regexOnPath + "]";
	}

}
