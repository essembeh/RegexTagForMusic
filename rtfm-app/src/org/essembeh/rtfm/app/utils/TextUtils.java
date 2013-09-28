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
package org.essembeh.rtfm.app.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TextUtils {

	/**
	 * 
	 * @param size
	 * @param name
	 * @return
	 */
	static public String plural(int size, String name) {
		return "" + size + " " + name + (size > 1 ? "s" : "");
	}

	/**
	 * 
	 * @param in
	 * @return
	 */
	static public List<String> toSortedList(Collection<String> in) {
		List<String> out = new ArrayList<>(in);
		Collections.sort(out);
		return Collections.unmodifiableList(out);
	}
}
