/**
 * Copyright 2012 Sebastien M-B <seb@essembeh.org>
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
package org.essembeh.rtfm.core.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.core.library.file.FileType;

/**
 * 
 * @author seb
 * 
 */
public class Workflow implements IWorkflowIdentifier {
	/**
	 * Attributes
	 */
	private final String identifier;
	private final String description;
	private final List<String> applyOnTypes;
	private final List<Task> tasks;

	/**
	 * Constructor
	 * 
	 * @param identifier
	 * @param description
	 */
	public Workflow(final String identifier, final String description) {
		this.description = description;
		this.identifier = identifier;
		applyOnTypes = new ArrayList<String>();
		tasks = new ArrayList<Task>();
	}

	/**
	 * Reference a type the action can handle.
	 * 
	 * @param type
	 */
	public void addSupportedType(final String type) {
		applyOnTypes.add(type);
	}

	/**
	 * Adds a task to the workflow
	 * 
	 * @param task
	 */
	public void addTask(final Task task) {
		tasks.add(task);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.core.actions.IWorkflowIdentifier#supportType(org.essembeh
	 * .rtfm.core.library.file.FileType)
	 */
	@Override
	public boolean supportType(final FileType type) {
		return applyOnTypes.contains(type.getIdentifier());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.actions.IWorkflowIdentifier#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.actions.IWorkflowIdentifier#getIdentifier()
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	public List<Task> getTaskList() {
		return Collections.unmodifiableList(tasks);
	}

	@Override
	public String toString() {
		return "Workflow [identifier=" + identifier + ", description=" + description + ", applyOnTypes="
				+ StringUtils.join(applyOnTypes, ",") + ", tasks=" + StringUtils.join(tasks, ",") + "]";
	}

	@Override
	public int compareTo(IWorkflowIdentifier o) {
		return identifier.compareTo(o.getIdentifier());
	}
}
