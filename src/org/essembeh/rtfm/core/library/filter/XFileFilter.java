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

import java.util.ArrayList;
import java.util.List;

import org.essembeh.rtfm.core.condition.AndCondition;
import org.essembeh.rtfm.core.library.file.IXFile;

public class XFileFilter extends AndCondition<IXFile> {

	/**
	 * 
	 * @param conditions
	 */
	public XFileFilter() {
		super();
	}

	/**
	 * 
	 * @param inList
	 * @return
	 */
	public List<IXFile> filter(List<IXFile> inList) {
		List<IXFile> outList = new ArrayList<IXFile>();
		for (IXFile xFile : inList) {
			if (isTrue(xFile)) {
				outList.add(xFile);
			}
		}
		return outList;
	}
}