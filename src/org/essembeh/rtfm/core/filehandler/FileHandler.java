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
package org.essembeh.rtfm.core.filehandler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.essembeh.rtfm.core.condition.AndCondition;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.library.file.IVirtualFile;

public class FileHandler {
	/**
	 * Attributes
	 */
	private final String fileType;
	private final List<IDynamicAttribute> attributes;
	private final AndCondition<IVirtualFile> conditions;

	/**
	 * 
	 * @param id
	 */
	public FileHandler(String id) {
		fileType = id;
		conditions = new AndCondition<IVirtualFile>();
		attributes = new ArrayList<IDynamicAttribute>();
	}

	/**
	 * 
	 * @return
	 */
	public String getType() {
		return fileType;
	}

	/**
	 * 
	 * @param attribute
	 */
	public void addAttribute(IDynamicAttribute attribute) {
		this.attributes.add(attribute);
	}

	/**
	 * 
	 * @return
	 */
	public AndCondition<IVirtualFile> getConditions() {
		return this.conditions;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @return the attributes
	 */
	public List<IDynamicAttribute> getAttributes() {
		return attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
