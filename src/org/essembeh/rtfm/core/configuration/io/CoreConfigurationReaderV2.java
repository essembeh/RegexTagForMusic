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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.Task;
import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.condition.AndCondition;
import org.essembeh.rtfm.core.condition.MultipleCondition;
import org.essembeh.rtfm.core.condition.OrCondition;
import org.essembeh.rtfm.core.condition.impl.virtualfile.AlwaysTrue;
import org.essembeh.rtfm.core.condition.impl.virtualfile.Extension;
import org.essembeh.rtfm.core.condition.impl.virtualfile.FileOrFolder;
import org.essembeh.rtfm.core.condition.impl.virtualfile.FileOrFolder.InodeType;
import org.essembeh.rtfm.core.condition.impl.virtualfile.VirtualPathMatches;
import org.essembeh.rtfm.core.condition.impl.xfile.AttributeExists;
import org.essembeh.rtfm.core.condition.impl.xfile.AttributeValueEquals;
import org.essembeh.rtfm.core.condition.impl.xfile.TypeEquals;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.filehandler.dynamic.RegexAttribute;
import org.essembeh.rtfm.core.library.file.IVirtualFile;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.string.StringSubstitutor;
import org.essembeh.rtfm.core.utils.version.JaxbReader;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;
import org.essembeh.rtfm.model.configuration.core.version2.TAction;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionAttributeExistsXFile;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionAttributeValueEqualsXFile;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionAttributeValueMatchesXFile;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionExtensionVirtualFile;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionFileOrFilderVirtualFile;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionGroupVirtualFile;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionGroupXFile;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionTrue;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionTypeEqualsXFile;
import org.essembeh.rtfm.model.configuration.core.version2.TConditionVirtualPathMatchesVirtualFile;
import org.essembeh.rtfm.model.configuration.core.version2.TCoreConfigurationV2;
import org.essembeh.rtfm.model.configuration.core.version2.TFileHandler;
import org.essembeh.rtfm.model.configuration.core.version2.TFixedAttribute;
import org.essembeh.rtfm.model.configuration.core.version2.TGroupLogic;
import org.essembeh.rtfm.model.configuration.core.version2.TProperty;
import org.essembeh.rtfm.model.configuration.core.version2.TReference;
import org.essembeh.rtfm.model.configuration.core.version2.TRegexAttribute;
import org.essembeh.rtfm.model.configuration.core.version2.TSubstitution;
import org.essembeh.rtfm.model.configuration.core.version2.TSubstitutionList;
import org.essembeh.rtfm.model.configuration.core.version2.TTask;

import com.google.inject.Inject;

public class CoreConfigurationReaderV2 extends JaxbReader<TCoreConfigurationV2> implements ICoreConfigurationProvider {
	/**
	 * Attributes
	 */
	private static final Logger logger = Logger.getLogger(CoreConfigurationReaderV2.class);
	private StringSubstitutor stringSubstitutor = null;

	/**
	 * Constructor
	 */
	@Inject
	public CoreConfigurationReaderV2() {
		super(TCoreConfigurationV2.class);
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
		// Get conditions
		fileHandler.getConditions().addCondition(read(model.getConditions()));
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
	private MultipleCondition<IVirtualFile> read(TConditionGroupVirtualFile model) {
		MultipleCondition<IVirtualFile> out;
		if (model.getLogic() == TGroupLogic.OR) {
			out = new OrCondition<IVirtualFile>();
		} else {
			out = new AndCondition<IVirtualFile>();
		}
		for (Object child : model.getTrueOrGroupOrVirtualPathMatches()) {
			if (child instanceof TConditionTrue) {
				out.addCondition(new AlwaysTrue<IVirtualFile>());
			} else if (child instanceof TConditionGroupVirtualFile) {
				TConditionGroupVirtualFile childObject = (TConditionGroupVirtualFile) child;
				out.addCondition(read(childObject));
			} else if (child instanceof TConditionVirtualPathMatchesVirtualFile) {
				TConditionVirtualPathMatchesVirtualFile childObject = (TConditionVirtualPathMatchesVirtualFile) child;
				out.addCondition(new VirtualPathMatches<IVirtualFile>(ss(childObject.getPattern())));
			} else if (child instanceof TConditionExtensionVirtualFile) {
				TConditionExtensionVirtualFile childObject = (TConditionExtensionVirtualFile) child;
				out.addCondition(new Extension<IVirtualFile>(ss(childObject.getList()), childObject.isCaseSensitive()));
			} else if (child instanceof TConditionFileOrFilderVirtualFile) {
				TConditionFileOrFilderVirtualFile childObject = (TConditionFileOrFilderVirtualFile) child;
				out.addCondition(new FileOrFolder<IVirtualFile>(InodeType.valueOf(childObject.getType())));
			} else {
				logger.error("Unsupported condition: " + child.getClass());
			}
		}
		return out;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private MultipleCondition<IXFile> read(TConditionGroupXFile model) {
		MultipleCondition<IXFile> out;
		if (model.getLogic() == TGroupLogic.OR) {
			out = new OrCondition<IXFile>();
		} else {
			out = new AndCondition<IXFile>();
		}
		for (Object child : model.getTrueOrGroupOrTypeEquals()) {
			if (child instanceof TConditionTrue) {
				out.addCondition(new AlwaysTrue<IXFile>());
			} else if (child instanceof TConditionGroupXFile) {
				TConditionGroupXFile childObject = (TConditionGroupXFile) child;
				out.addCondition(read(childObject));
			} else if (child instanceof TConditionVirtualPathMatchesVirtualFile) {
				TConditionVirtualPathMatchesVirtualFile childObject = (TConditionVirtualPathMatchesVirtualFile) child;
				out.addCondition(new VirtualPathMatches<IXFile>(ss(childObject.getPattern())));
			} else if (child instanceof TConditionExtensionVirtualFile) {
				TConditionExtensionVirtualFile childObject = (TConditionExtensionVirtualFile) child;
				out.addCondition(new Extension<IXFile>(ss(childObject.getList()), childObject.isCaseSensitive()));
			} else if (child instanceof TConditionTypeEqualsXFile) {
				TConditionTypeEqualsXFile childObject = (TConditionTypeEqualsXFile) child;
				out.addCondition(new TypeEquals<IXFile>(childObject.getValue()));
			} else if (child instanceof TConditionAttributeExistsXFile) {
				TConditionAttributeExistsXFile childObject = (TConditionAttributeExistsXFile) child;
				out.addCondition(new AttributeExists<IXFile>(childObject.getName(), childObject.isExists()));
			} else if (child instanceof TConditionAttributeValueEqualsXFile) {
				TConditionAttributeValueEqualsXFile childObject = (TConditionAttributeValueEqualsXFile) child;
				out.addCondition(new AttributeValueEquals<IXFile>(childObject.getName(), childObject.getValue()));
			} else if (child instanceof TConditionAttributeValueMatchesXFile) {
				TConditionAttributeValueMatchesXFile childObject = (TConditionAttributeValueMatchesXFile) child;
				out.addCondition(new AttributeValueEquals<IXFile>(childObject.getName(), ss(childObject.getPattern())));
			} else if (child instanceof TConditionFileOrFilderVirtualFile) {
				TConditionFileOrFilderVirtualFile childObject = (TConditionFileOrFilderVirtualFile) child;
				out.addCondition(new FileOrFolder<IXFile>(InodeType.valueOf(childObject.getType())));
			} else {
				logger.error("Unsupported condition: " + child.getClass());
			}
		}
		return out;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private IDynamicAttribute read(TRegexAttribute model) {
		IDynamicAttribute o = new RegexAttribute(model.getName(), Pattern.compile(ss(model.getPattern())), model.getGroup(), model.isOptional());
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
		out.getConditions().addCondition(read(model.getConditions()));
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
		if (stringSubstitutor == null && modelAvailable()) {
			stringSubstitutor = read(getModel().getSubstitutions());
		}
		return stringSubstitutor == null ? input : stringSubstitutor.apply(input);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider#getFileHandlers()
	 */
	@Override
	public List<FileHandler> getFileHandlers() {
		List<FileHandler> out = new ArrayList<FileHandler>();
		if (modelAvailable()) {
			for (TFileHandler fileHandlerModel : getModel().getFilehandlers().getFilehandler()) {
				FileHandler theFileHandler = read(fileHandlerModel);
				logger.debug("Found filehandler: " + theFileHandler);
				out.add(theFileHandler);
			}
		}
		return out;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider#getWorkflows()
	 */
	@Override
	public List<Workflow> getWorkflows() {
		List<Workflow> out = new ArrayList<Workflow>();
		if (modelAvailable()) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.utils.version.JaxbReader#read(java.io.InputStream)
	 */
	@Override
	public void read(InputStream inputStream) throws ReaderException {
		stringSubstitutor = null;
		super.read(inputStream);
	}
}
