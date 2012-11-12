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

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.Task;
import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.condition.AndCondition;
import org.essembeh.rtfm.core.condition.ICondition;
import org.essembeh.rtfm.core.condition.MultipleCondition;
import org.essembeh.rtfm.core.condition.OrCondition;
import org.essembeh.rtfm.core.condition.impl.virtualfile.FileOrFolder;
import org.essembeh.rtfm.core.condition.impl.virtualfile.FileOrFolder.InodeType;
import org.essembeh.rtfm.core.condition.impl.virtualfile.VirtualPathMatches;
import org.essembeh.rtfm.core.configuration.CoreConfiguration;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.filehandler.dynamic.RegexAttribute;
import org.essembeh.rtfm.core.library.file.VirtualFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.string.StringSubstitutor;
import org.essembeh.rtfm.core.utils.version.JaxbObjectReader;
import org.essembeh.rtfm.model.configuration.core.version2.TAction;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionLogic;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionOnType;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionOnVirtualPath;
import org.essembeh.rtfm.model.configuration.core.version2.TCoreConfigurationV2;
import org.essembeh.rtfm.model.configuration.core.version2.TFileHandler;
import org.essembeh.rtfm.model.configuration.core.version2.TFixedAttribute;
import org.essembeh.rtfm.model.configuration.core.version2.TProperty;
import org.essembeh.rtfm.model.configuration.core.version2.TReference;
import org.essembeh.rtfm.model.configuration.core.version2.TRegexAttribute;
import org.essembeh.rtfm.model.configuration.core.version2.TSubstitution;
import org.essembeh.rtfm.model.configuration.core.version2.TSubstitutionList;
import org.essembeh.rtfm.model.configuration.core.version2.TTask;

import com.google.inject.Inject;

public class CoreConfigurationLoaderV2 extends JaxbObjectReader<CoreConfiguration, TCoreConfigurationV2> {
	/**
	 * Attributes
	 */
	private static final Logger logger = Logger.getLogger(CoreConfigurationLoaderV2.class);
	private StringSubstitutor stringSubstitutor = null;

	/**
	 * Constructor
	 */
	@Inject
	public CoreConfigurationLoaderV2() {
		super(TCoreConfigurationV2.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.JaxbObjectReader#readObjectFromModel(java.lang.Object, org.essembeh.rtfm.core.utils.version.ILoadable)
	 */
	@Override
	protected void readObjectFromModel(TCoreConfigurationV2 model, CoreConfiguration configuration) {
		// String substitutions
		stringSubstitutor = read(model.getSubstitutions());
		// Filehandlers
		for (TFileHandler fileHandlerModel : model.getFilehandlers().getFilehandler()) {
			FileHandler theFileHandler = read(fileHandlerModel);
			logger.debug("Found filehandler: " + theFileHandler);
			configuration.getFileHandlers().add(theFileHandler);
		}
		// Tasks
		Map<String, Task> tasks = new HashMap<String, Task>();
		for (TTask taskModel : model.getTasks().getTask()) {
			try {
				Task task = read(taskModel);
				logger.debug("Found task: " + task);
				tasks.put(task.getIdentifier(), task);
			} catch (ConfigurationException e) {
				logger.error(e.getMessage());
			}
		}
		// Workflows
		for (TAction actionModel : model.getActions().getAction()) {
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
				configuration.getWorkflows().add(action);
			} catch (ConfigurationException e) {
				logger.error(e.getMessage());
			}
		}
	}

	/**
	 * 
	 * @param substitutions
	 * @return
	 */
	private StringSubstitutor read(TSubstitutionList substitutions) {
		StringSubstitutor out = new StringSubstitutor();
		if (substitutions != null) {
			for (TSubstitution sub : substitutions.getSubstitution()) {
				String key = sub.getKey();
				String value = sub.getValue();
				logger.debug("Adding substitution: " + key + " --> " + value);
				out.addSubstitution(key, value);
			}
		}
		return out;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private FileHandler read(TFileHandler model) {
		String id = model.getId();
		FileHandler fileHandler = new FileHandler(id);
		// Get logic
		TConditionLogic logic = model.getConditions().getLogic();
		MultipleCondition<VirtualFile> globalCondition;
		if (TConditionLogic.AND == logic) {
			globalCondition = new AndCondition<VirtualFile>();
		} else {
			globalCondition = new OrCondition<VirtualFile>();
		}
		// Conditions
		if (model.getConditions() != null) {
			for (TConditionOnVirtualPath virtualPathModel : model.getConditions().getVirtualpath()) {
				ICondition<VirtualFile> condition = new VirtualPathMatches(ss(virtualPathModel.getPattern()));
				logger.debug("Found condition: " + condition);
				globalCondition.addCondition(condition);
			}
			for (TConditionOnType conditionModel : model.getConditions().getType()) {
				ICondition<VirtualFile> condition = new FileOrFolder(InodeType.valueOf(conditionModel.getValue().toString()));
				logger.debug("Found condition: " + condition);
				globalCondition.addCondition(condition);
			}
		}
		fileHandler.getConditions().addCondition(globalCondition);
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
		IDynamicAttribute o = new RegexAttribute(model.getName(), Pattern.compile(ss(model.getPattern())), model.getGroup(),
				model.isOptional());
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
				out.setProperty(ss(property.getName()), ss(property.getValue()));
			}
		} catch (TaskException e) {
			throw new ConfigurationException(e.getMessage());
		}
		return out;
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	private String ss(String input) {
		return stringSubstitutor == null ? input : stringSubstitutor.apply(input);
	}
}
