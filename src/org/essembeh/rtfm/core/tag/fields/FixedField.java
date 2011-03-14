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
package org.essembeh.rtfm.core.tag.fields;

import org.essembeh.rtfm.interfaces.ITagField;

public class FixedField implements ITagField {

	protected String fixedValue;

	/**
	 * 
	 * @param value
	 */
	public FixedField(String value) {
		this.fixedValue = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.tag.ITagField#getValue(java.lang.String)
	 */
	@Override
	public String getValue(String path) {
		return this.fixedValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FixedField [fixedValue=" + this.fixedValue + "]";
	}

}
