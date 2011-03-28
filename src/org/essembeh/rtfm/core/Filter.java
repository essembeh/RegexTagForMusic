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

import org.apache.log4j.Logger;

public class Filter {

	/**
	 * Class logger
	 */
	static Logger logger = Logger.getLogger(Filter.class);
	
	/** COMMON FILTERS **/
	public static Filter INVALID = new Filter(Status.NO_FILTER, Status.NO_FILTER);
	public static Filter TAGGABLE = new Filter(Status.ENABLE, Status.NO_FILTER);
	public static Filter NON_TAGGED = new Filter(Status.ENABLE, Status.INVERSE);

	public enum Status {
		ENABLE, INVERSE, NO_FILTER
	}

	protected Status taggable;
	protected Status tagged;
	protected Pattern type;
	protected Pattern path;

	public Filter() {
		this.taggable = Status.NO_FILTER;
		this.tagged = Status.NO_FILTER;
		this.type = null;
		this.path = null;
	}

	/**
	 * 
	 * @param taggable
	 * @param tagged
	 */
	public Filter(Status taggable, Status tagged) {
		this.taggable = taggable;
		this.tagged = tagged;
		this.type = null;
		this.path = null;
	}

	/**
	 * @param taggable
	 * @param tagged
	 * @param type
	 */
	public Filter(Status taggable, Status tagged, Pattern type, Pattern path) {
		this.taggable = taggable;
		this.tagged = tagged;
		this.type = type;
		this.path = path;
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
	 * @param path
	 *            the path to set
	 */
	public void setPath(Pattern path) {
		this.path = path;
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
			if (matches(musicFile.isTaggable(), this.taggable) && matches(musicFile.isTagged(), this.tagged)) {
				if (this.type != null && !this.type.matcher(musicFile.getType()).matches()) {
					Filter.logger.debug("Type do not match filter: " + musicFile.getType());
					continue;
				} else if (this.path != null && !this.path.matcher(musicFile.getVirtualPath()).matches()) {
					Filter.logger.debug("Virtual Path do not match filter: " + musicFile.getVirtualPath());
					continue;
				} else {
					result.add(musicFile);
				}
			}
		}
		return result;
	}

}
