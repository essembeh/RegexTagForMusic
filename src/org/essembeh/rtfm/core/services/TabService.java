/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of rtfm.
 * 
 * rtfm is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * rtfm is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * rtfm. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.core.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.Filter;

public class TabService {

	/**
	 * Class logger
	 */
	static final private Logger logger = Logger.getLogger(TabService.class);

	/**
	 * The data
	 */
	protected List<String> tabNames = new ArrayList<String>();
	protected List<Filter> filters = new ArrayList<Filter>();

	/**
	 * 
	 * @param tabname
	 * @param filter
	 */
	public void addTabFilter(String tabname, Filter filter) {
		this.tabNames.add(tabname);
		logger.debug("Adding new Tab, Name: " + tabname + ", Filter: " + filter);
		this.filters.add(filter);
	}

	/**
	 * 
	 * @return
	 */
	public int getTabCount() {
		return this.tabNames.size();
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public String getTabName(int i) {
		return this.tabNames.get(i);
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	public Filter getTabFilter(int i) {
		return this.filters.get(i);
	}
}
