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
package org.essembeh.rtfm.core.library.file.attributes;

public class Attribute implements Cloneable, Comparable<Attribute> {

	String name;
	String value;
	boolean hidden;

	public Attribute(String name, String value, boolean hidden) {
		this.name = name;
		this.value = value;
		this.hidden = hidden;
	}

	public boolean isHidden() {
		return hidden;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public boolean setValue(String newValue) {
		this.value = newValue;
		return true;
	}

	@Override
	public Attribute clone() {
		return new Attribute(name, value, hidden);
	}

	@Override
	public String toString() {
		return (hidden ? "-" : "+") + name + "=" + value;
	}

	@Override
	public int compareTo(Attribute o) {
		return name.compareTo(o.name);
	}
}
