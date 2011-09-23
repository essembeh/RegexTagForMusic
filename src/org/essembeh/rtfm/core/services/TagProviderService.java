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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.interfaces.ITagProvider;

public class TagProviderService {

	/**
	 * Class logger
	 */
	static final private Logger logger = Logger.getLogger(TagProviderService.class);

	/**
	 * The data
	 */
	protected Map<String, ITagProvider> data = new HashMap<String, ITagProvider>();

	/**
	 * Registers a tag provider
	 * 
	 * @param id
	 *            the identifier of the tag provider
	 * @param object
	 *            the tag provider
	 */
	public void add(String id, ITagProvider object) {
		this.data.put(id, object);
		logger.debug("Adding new ITagProvider for id: " + id);
	}

	/**
	 * Gets a specific tag provider
	 * 
	 * @param id
	 * @return
	 */
	public ITagProvider get(String id) {
		ITagProvider object = this.data.get(id);
		logger.debug("Get the ITagProvider, id: " + id + ", Object: " + object);
		return object;
	}

	/**
	 * Return the list Of All Tag Providers ID
	 * 
	 * @return
	 */
	public List<String> getAllIds() {
		return new ArrayList<String>(this.data.keySet());
	}
}
