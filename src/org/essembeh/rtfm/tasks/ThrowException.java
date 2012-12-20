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
package org.essembeh.rtfm.tasks;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IXFile;

public class ThrowException implements ITask {

	private String message = "Default message";

	@Override
	public void execute(IXFile file) throws ActionException {
		throw new ActionException(message);
	}

	@Override
	public void setProperty(String key, String value) {
		if ("message".equalsIgnoreCase(key)) {
			message = value;
		}
	}
}
