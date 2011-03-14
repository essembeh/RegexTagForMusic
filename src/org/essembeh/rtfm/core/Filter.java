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
package org.essembeh.rtfm.core;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Filter {

	/** COMMON FILTERS **/
	public static Filter VALID = new Filter(Status.ENABLE, Status.NO_FILTER, Status.NO_FILTER, null);
	public static Filter INVALID = new Filter(Status.INVERSE, Status.NO_FILTER, Status.NO_FILTER, null);
	public static Filter TAGGABLE = new Filter(Status.NO_FILTER, Status.ENABLE, Status.NO_FILTER, null);
	public static Filter NON_TAGGED = new Filter(Status.NO_FILTER, Status.ENABLE, Status.INVERSE, null);
	
	public enum Status {
		ENABLE, INVERSE, NO_FILTER
	}

	protected Status valid;
	protected Status taggable;
	protected Status tagged;
	protected Pattern type;

	public Filter() {
		this.valid = Status.NO_FILTER;
		this.taggable = Status.NO_FILTER;
		this.tagged = Status.NO_FILTER;
		this.type = null;
	}

	/**
	 * @param valid
	 * @param taggable
	 * @param tagged
	 * @param type
	 */
	public Filter(Status valid, Status taggable, Status tagged, Pattern type) {
		this.valid = valid;
		this.taggable = taggable;
		this.tagged = tagged;
		this.type = type;
	}

	/**
	 * @param valid
	 *            the valid to set
	 */
	public void setValid(Status valid) {
		this.valid = valid;
	}

	/**
	 * @param taggable
	 *            the taggable to set
	 */
	public void setTaggable(Status taggable) {
		this.taggable = taggable;
	}

	/**
	 * @param tagged
	 *            the tagged to set
	 */
	public void setTagged(Status tagged) {
		this.tagged = tagged;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Pattern type) {
		this.type = type;
	}

	/**
	 * 
	 * @param condition
	 * @param status
	 * @return
	 */
	protected boolean matches(boolean condition, Status status) {
		boolean match = true;
		if (status == Status.INVERSE) {
			match = !condition;
		} else if (status == Status.ENABLE) {
			match = condition;
		}
		return match;
	}

	/**
	 * 
	 * @param list
	 * @return
	 */
	public List<MusicFile> filter(List<MusicFile> list) {
		List<MusicFile> result = new ArrayList<MusicFile>();

		for (MusicFile musicFile : list) {
			if (matches(musicFile.isValid(), this.valid) && matches(musicFile.isTaggable(), this.taggable)
					&& matches(musicFile.isTagged(), this.tagged)) {
				if (this.type == null || this.type.matcher(musicFile.getType()).matches()) {
					result.add(musicFile);
				}
			}
		}
		return result;
	}

}
