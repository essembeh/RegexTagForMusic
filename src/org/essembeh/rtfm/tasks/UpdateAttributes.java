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

import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.utils.commoninterfaces.impl.AbstractConfigurable;

public class UpdateAttributes extends AbstractConfigurable implements IExecutable {
	private static final Logger logger = Logger.getLogger(UpdateAttributes.class);

	@Override
	public void execute(IXFile file) {
		for (Entry<String, String> e : getProperties().entrySet()) {
			if (file.getAttributes().updateOrCreate(e.getKey(), e.getValue())) {
				logger.debug("Set attribute: " + e.getKey() + " = " + e.getValue());
			} else {
				logger.debug("Create attribute: " + e.getKey() + " = " + e.getValue());
			}
		}
	}
}
