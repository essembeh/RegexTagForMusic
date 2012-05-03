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
import org.essembeh.rtfm.core.utils.identifiers.DynamicAttributeIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public class FileHandler {

	private String identifier;

	private List<ICondition> conditions;

	IdList<Attribute, Identifier<Attribute>> simpleAttributes;

	IdList<IDynamicAttribute, Identifier<IDynamicAttribute>> dynamicAttributes;

	public FileHandler(String id) {
		this.identifier = id;
		conditions = new ArrayList<ICondition>();
		simpleAttributes = new IdList<Attribute, Identifier<Attribute>>(new AttributeIdentifier());
		dynamicAttributes = new IdList<IDynamicAttribute, Identifier<IDynamicAttribute>>(
				new DynamicAttributeIdentifier());
	}

	public FileType getType() {
		return FileType.createFiletype(identifier);
	}

	public void addAttribute(Attribute attribute) {
		this.simpleAttributes.add(attribute);
	}

	public void addDynamicAttribute(IDynamicAttribute attribute) {
		this.dynamicAttributes.add(attribute);
	}

	public void addCondition(ICondition condition) {
		this.conditions.add(condition);
	}

	public boolean canHandle(VirtualFile file) {
		for (ICondition condition : conditions) {
			if (condition.isValid(file)) {
				return true;
			}
		}
		return false;
	}

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

	@Override
	public String toString() {
		return "FileHandler [identifier=" + identifier + "]";
	}
}
