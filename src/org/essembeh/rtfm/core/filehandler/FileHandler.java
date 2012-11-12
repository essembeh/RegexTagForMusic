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

import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.identifiers.AttributeIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public class FileHandler {

	public enum Logic {
		AND, OR
	};

	/**
	 * Attributes
	 */
	private final String identifier;
	private final List<ICondition> conditions;
	private final List<Attribute> simpleAttributes;
	private final List<IDynamicAttribute> dynamicAttributes;
	private final Logic logic;

	/**
	 * 
	 * @param id
	 */
	public FileHandler(String id, Logic logic) {
		this.identifier = id;
		this.logic = logic;
		conditions = new ArrayList<ICondition>();
		simpleAttributes = new ArrayList<Attribute>();
		dynamicAttributes = new ArrayList<IDynamicAttribute>();
	}

	/**
	 * 
	 * @return
	 */
	public FileType getType() {
		return FileType.createFiletype(identifier);
	}

	/**
	 * 
	 * @param attribute
	 */
	public void addAttribute(Attribute attribute) {
		this.simpleAttributes.add(attribute);
	}

	/**
	 * 
	 * @param attribute
	 */
	public void addDynamicAttribute(IDynamicAttribute attribute) {
		this.dynamicAttributes.add(attribute);
	}

	/**
	 * 
	 * @param condition
	 */
	public void addCondition(ICondition condition) {
		this.conditions.add(condition);
	}

	/**
	 * 
	 * @param file
	 * @return
	 */
	public boolean canHandle(VirtualFile file) {
		if (conditions.size() == 0) {
			return true;
		}
		for (ICondition condition : conditions) {
			boolean valid = condition.isValid(file);
			if (valid && logic == Logic.OR) {
				return true;
			} else if (!valid && logic == Logic.AND) {
				return false;
			}
		}
		return logic == Logic.AND ? true : false;
	}

	/**
	 * 
	 * @param virtualFile
	 * @return
	 * @throws DynamicAttributeException
	 */
	public IdList<Attribute, Identifier<Attribute>> getAttributesForFile(VirtualFile virtualFile)
			throws DynamicAttributeException {
		IdList<Attribute, Identifier<Attribute>> attributes = new IdList<Attribute, Identifier<Attribute>>(
				new AttributeIdentifier());

		for (Attribute attribute : simpleAttributes) {
			attributes.add(attribute.clone());
		}
		for (IDynamicAttribute attribute : dynamicAttributes) {
			attributes.add(attribute.createAttribute(virtualFile));
		}
		return attributes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileHandler [identifier=" + identifier + "]";
	}
}
