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
package org.essembeh.rtfm.ui.utils;

public enum Image {

	FILE_NEW("document-new.png"),
	FILE_OPEN("document-open.png"),
	FILE_SAVE("document-save.png"),
	INSPECT("inspect.png"),
	RUN_SELECTION("run-selection.png"),
	RUN_ALL("run-all.png"),
	ADD("add.png"),
	CANCEL("cancel.png");

	/**
	 * Image file name
	 */
	public final String filename;

	/**
	 * Constructor which set the image file name which the one given in
	 * parameters
	 * 
	 * @param name
	 */
	Image(String name) {
		this.filename = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return filename;
	}
}