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

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.filter.conditions.IFilterCondition;

public class Filter {

	/**
	 * Attributes
	 */
	private final List<IFilterCondition> conditions;

	/**
	 * 
	 * @param conditions
	 */
	public Filter(IFilterCondition... conditions) {
		this.conditions = new ArrayList<IFilterCondition>();
		addConditions(conditions);
	}

	/**
	 * 
	 * @param conditions
	 */
	public void addConditions(IFilterCondition... conditions) {
		if (conditions != null) {
			for (int i = 0; i < conditions.length; i++) {
				addCondition(conditions[i]);
			}
		}
	}

	/**
	 * 
	 * @param condition
	 */
	public void addCondition(IFilterCondition condition) {
		conditions.add(condition);
	}

	/**
	 * 
	 * @param musicFile
	 * @return
	 */
	protected boolean matches(IMusicFile musicFile) {
		boolean matches = true;
		for (IFilterCondition condition : conditions) {
			if (!condition.isTrue(musicFile)) {
				matches = false;
				break;
			}
		}
		return matches;
	}

	/**
	 * 
	 * @param inList
	 * @return
	 */
	public List<IMusicFile> filter(List<IMusicFile> inList) {
		List<IMusicFile> outList = new ArrayList<IMusicFile>();
		for (IMusicFile musicFile : inList) {
			if (matches(musicFile)) {
				outList.add(musicFile);
			}
		}
		return outList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Filter [conditions={" + StringUtils.join(conditions, ", ") + "}]";
	}
}
