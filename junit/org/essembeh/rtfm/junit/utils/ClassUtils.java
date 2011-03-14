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

package org.essembeh.rtfm.junit.utils;

import java.lang.reflect.Field;

import org.apache.log4j.Logger;

public class ClassUtils {

	static Logger logger = Logger.getLogger(ClassUtils.class);

	/**
	 * 
	 * @param o
	 * @param field
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Object getPrivateAttribute(Object o, String field) throws IllegalArgumentException,
			IllegalAccessException {
		Class<?> clazz = o.getClass();
		Field f = null;
		Object r = null;
		while ((clazz != Object.class) && (f == null)) {
			try {
				f = clazz.getDeclaredField(field);
			} catch (Exception e) {
				clazz = clazz.getSuperclass();
			}
		}
		if (f != null) {
			f.setAccessible(true);
			r = f.get(o);
		}
		return r;
	}

}
