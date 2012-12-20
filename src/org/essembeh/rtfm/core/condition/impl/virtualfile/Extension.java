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
package org.essembeh.rtfm.core.condition.impl.virtualfile;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.library.file.IVirtualFile;

import com.google.common.io.Files;

public class Extension<T extends IVirtualFile> implements ICondition<T> {

	private final List<String> extensions;
	private final boolean caseSensitive;

	/**
	 * 
	 * @param regexOnPath
	 */
	public Extension(String extensionsList, boolean caseSensitive) {
		this.extensions = new ArrayList<String>();
		this.caseSensitive = caseSensitive;
		for (String ext : extensionsList.split(" ")) {
			extensions.add(caseSensitive ? ext : ext.toLowerCase());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.condition.ICondition#isTrue(java.lang.Object)
	 */
	@Override
	public boolean isTrue(T input) {
		String extension = Files.getFileExtension(input.getVirtualPath());
		return extensions.contains(caseSensitive ? extension : extension.toLowerCase());
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
