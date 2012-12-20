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
import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IVirtualFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.identifiers.AttributeIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;

public class FileHandler {
	/**
	 * Attributes
	 */
	private final FileType fileType;
	private final List<Attribute> simpleAttributes;
	private final List<IDynamicAttribute> dynamicAttributes;
	private final AndCondition<IVirtualFile> conditions;

	/**
	 * 
	 * @param id
	 */
	public FileHandler(String id) {
		fileType = FileType.createFiletype(id);
		conditions = new AndCondition<IVirtualFile>();
		simpleAttributes = new ArrayList<Attribute>();
		dynamicAttributes = new ArrayList<IDynamicAttribute>();
	}

	/**
	 * 
	 * @return
	 */
	public FileType getType() {
		return fileType;
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
	 * @return
	 */
	public AndCondition<IVirtualFile> getConditions() {
		return this.conditions;
	}

	/**
	 * 
	 * @param virtualFile
	 * @return
	 */
	public IdList<Attribute, Identifier<Attribute>> getAttributesForFile(VirtualFile virtualFile) {
		IdList<Attribute, Identifier<Attribute>> attributes = new IdList<Attribute, Identifier<Attribute>>(new AttributeIdentifier());

		for (Attribute attribute : simpleAttributes) {
			attributes.add(attribute.clone());
		}
		for (IDynamicAttribute attribute : dynamicAttributes) {
			try {
				attributes.add(attribute.createAttribute(virtualFile));
			} catch (DynamicAttributeException e) {
				attributes.add(createErrorAttribute(e));
			}
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
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	private Attribute createErrorAttribute(Throwable t) {
		// TODO: Properties
		Attribute out = new Attribute("core:error", t.getMessage());
		return out;
	}
}
