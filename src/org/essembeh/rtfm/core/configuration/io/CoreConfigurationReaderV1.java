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
package org.essembeh.rtfm.core.configuration.io;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.Task;
import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.condition.impl.virtualfile.VirtualPathMatches;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.filehandler.dynamic.RegexAttribute;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.version.JaxbReader;
import org.essembeh.rtfm.model.configuration.core.version1.TAction;
import org.essembeh.rtfm.model.configuration.core.version1.TConditionOnVirtualPath;
import org.essembeh.rtfm.model.configuration.core.version1.TCoreConfigurationV1;
import org.essembeh.rtfm.model.configuration.core.version1.TFileHandler;
import org.essembeh.rtfm.model.configuration.core.version1.TFixedAttribute;
import org.essembeh.rtfm.model.configuration.core.version1.TProperty;
import org.essembeh.rtfm.model.configuration.core.version1.TReference;
import org.essembeh.rtfm.model.configuration.core.version1.TRegexAttribute;
import org.essembeh.rtfm.model.configuration.core.version1.TTask;

import com.google.inject.Inject;

public class CoreConfigurationReaderV1 extends JaxbReader<TCoreConfigurationV1> implements ICoreConfigurationProvider {
	/**
	 * Attributes
	 */
	private static final Logger logger = Logger.getLogger(CoreConfigurationReaderV1.class);

	/**
	 * Constructor
	 */
	@Inject
	public CoreConfigurationReaderV1() {
		super(TCoreConfigurationV1.class);
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private FileHandler read(TFileHandler model) {
		String id = model.getId();
		FileHandler fileHandler = new FileHandler(id);
		// Conditions
		if (model.getConditions() != null) {
			for (TConditionOnVirtualPath virtualPathModel : model.getConditions().getVirtualpath()) {
				fileHandler.getConditions().addCondition(new VirtualPathMatches(virtualPathModel.getMatches()));
			}
		}
		// Attributes
		if (model.getAttributes() != null) {
			for (TFixedAttribute fixedAttribute : model.getAttributes().getAttribute()) {
				fileHandler.addAttribute(read(fixedAttribute));
			}
			for (TRegexAttribute regexAttribute : model.getAttributes().getRegexAttribute()) {
				fileHandler.addDynamicAttribute(read(regexAttribute));
			}
		}
		logger.debug("Create Filehandler: " + fileHandler);
		return fileHandler;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private IDynamicAttribute read(TRegexAttribute model) {
		IDynamicAttribute o = new RegexAttribute(model.getName(), Pattern.compile(model.getPattern()), model.getGroup(), model.isOptional());
		logger.debug("Create DynamicAttribute: " + o);
		return o;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private Attribute read(TFixedAttribute model) {
		Attribute o = new Attribute(model.getName(), model.getValue());
		logger.debug("Create Attribute: " + o);
		return o;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private Workflow read(TAction model) {
		Workflow out = new Workflow(model.getId(), model.getDescription());
		for (TReference ref : model.getApplyOn().getFilehandler()) {
			out.addSupportedType(ref.getRefId());
		}
		return out;
	}

	/**
	 * 
	 * @param model
	 * @return
	 * @throws ConfigurationException
	 */
	private Task read(TTask model) throws ConfigurationException {
		Task out;
		try {
			String id = model.getId();
			String classname = model.getClassname();
			out = new Task(id, classname);
			// Setting properties
			for (TProperty property : model.getProperty()) {
				out.setProperty(property.getName(), property.getValue());
			}
		} catch (TaskException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider#getFileHandlers()
	 */
	@Override
	public List<FileHandler> getFileHandlers() {
		List<FileHandler> out = new ArrayList<FileHandler>();
		if (getModel() != null) {
			for (TFileHandler fileHandlerModel : getModel().getFilehandlers().getFilehandler()) {
				FileHandler theFileHandler = read(fileHandlerModel);
				logger.debug("Found filehandler: " + theFileHandler);
				out.add(theFileHandler);
			}
		}
		return out;
	}

	@Override
	public List<Workflow> getWorkflows() {
		List<Workflow> out = new ArrayList<Workflow>();
		if (getModel() != null) {
			// Tasks
			Map<String, Task> tasks = new HashMap<String, Task>();
			for (TTask taskModel : getModel().getTasks().getTask()) {
				try {
					Task task = read(taskModel);
					logger.debug("Found task: " + task);
					tasks.put(task.getIdentifier(), task);
				} catch (ConfigurationException e) {
					logger.error(e.getMessage());
				}
			}
			// Workflows
			for (TAction actionModel : getModel().getActions().getAction()) {
				Workflow action = read(actionModel);
				try {
					// Tasks
					for (TReference taskRef : actionModel.getWorkflow().getTask()) {
						Task taskExecutor = tasks.get(taskRef.getRefId());
						if (taskExecutor == null) {
							throw new ConfigurationException("Cannot find task: " + taskRef.getRefId());
						}
						action.addTask(taskExecutor);
					}
					logger.debug("Found workflow: " + action);
					out.add(action);
				} catch (ConfigurationException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return out;
	}

}
