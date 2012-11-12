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
package org.essembeh.rtfm.core.filehandler;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.library.file.VirtualFile;

public class RegexOnPathCondition implements ICondition {

	/**
	 * Attributes
	 */
	private final Pattern pattern;

	/**
	 * 
	 * @param regex
	 */
	public RegexOnPathCondition(String regex) {
		pattern = Pattern.compile(regex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.filehandler.ICondition#isValid(org.essembeh.rtfm .core.library.file.VirtualFile)
	 */
	@Override
	public boolean isValid(VirtualFile file) {
		return pattern.matcher(file.getVirtualPath()).matches();
	}

}
