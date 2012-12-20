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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.essembeh.rtfm.core.condition.AndCondition;
import org.essembeh.rtfm.core.library.file.IXFile;

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
	private final AndCondition<IXFile> conditions;
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
		conditions = new AndCondition<IXFile>();
		tasks = new ArrayList<Task>();
	}

	/**
	 * 
	 * @return
	 */
	public AndCondition<IXFile> getConditions() {
		return this.conditions;
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
	 * @see org.essembeh.rtfm.core.actions.IWorkflowIdentifier#supportFile(org.essembeh.rtfm.core.library.file.IXFile)
	 */
	@Override
	public boolean supportFile(IXFile file) {
		return conditions.isTrue(file);
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

	/**
	 * 
	 * @return
	 */
	public List<Task> getTaskList() {
		return Collections.unmodifiableList(tasks);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IWorkflowIdentifier o) {
		return identifier.compareTo(o.getIdentifier());
	}
}
