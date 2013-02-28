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
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.condition.AndCondition;
import org.essembeh.rtfm.core.condition.ICondition;
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
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.filehandler.dynamic.RegexAttribute;
import org.essembeh.rtfm.core.filehandler.dynamic.SimpleAttribute;
import org.essembeh.rtfm.core.library.file.IVirtualFile;
import org.essembeh.rtfm.core.library.file.IXFile;
import org.essembeh.rtfm.core.utils.commoninterfaces.IConfigurable;
import org.essembeh.rtfm.core.utils.string.StringSubstitutor;
import org.essembeh.rtfm.core.utils.version.JaxbReader;
import org.essembeh.rtfm.core.utils.version.exceptions.ReaderException;
import org.essembeh.rtfm.core.workflow.TaskDescription;
import org.essembeh.rtfm.core.workflow.Workflow;
import org.essembeh.rtfm.model.configuration.core.TAction;
import org.essembeh.rtfm.model.configuration.core.TConditionAttributeExistsXFile;
import org.essembeh.rtfm.model.configuration.core.TConditionAttributeValueEqualsXFile;
import org.essembeh.rtfm.model.configuration.core.TConditionAttributeValueMatchesXFile;
import org.essembeh.rtfm.model.configuration.core.TConditionExtensionVirtualFile;
import org.essembeh.rtfm.model.configuration.core.TConditionFileOrFilderVirtualFile;
import org.essembeh.rtfm.model.configuration.core.TConditionGroupVirtualFile;
import org.essembeh.rtfm.model.configuration.core.TConditionGroupXFile;
import org.essembeh.rtfm.model.configuration.core.TConditionTrue;
import org.essembeh.rtfm.model.configuration.core.TConditionTypeEqualsXFile;
import org.essembeh.rtfm.model.configuration.core.TConditionVirtualPathMatchesVirtualFile;
import org.essembeh.rtfm.model.configuration.core.TCoreConfiguration;
import org.essembeh.rtfm.model.configuration.core.TFileHandler;
import org.essembeh.rtfm.model.configuration.core.TFixedAttribute;
import org.essembeh.rtfm.model.configuration.core.TGroupLogic;
import org.essembeh.rtfm.model.configuration.core.TProperty;
import org.essembeh.rtfm.model.configuration.core.TReference;
import org.essembeh.rtfm.model.configuration.core.TRegexAttribute;
import org.essembeh.rtfm.model.configuration.core.TSubstitution;
import org.essembeh.rtfm.model.configuration.core.TSubstitutionList;
import org.essembeh.rtfm.model.configuration.core.TTask;

import com.google.inject.Inject;

public class CoreConfigurationReader extends JaxbReader<TCoreConfiguration> implements ICoreConfigurationProvider {
	/**
	 * Attributes
	 */
	private static final Logger logger = Logger.getLogger(CoreConfigurationReader.class);
	private StringSubstitutor stringSubstitutor = null;

	/**
	 * Constructor
	 */
	@Inject
	public CoreConfigurationReader() {
		super(TCoreConfiguration.class);
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
				fileHandler.addAttribute(read(regexAttribute));
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
	private ICondition<IVirtualFile> read(TConditionGroupVirtualFile model) {
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
	private ICondition<IXFile> read(TConditionGroupXFile model) {
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
	private IDynamicAttribute read(TFixedAttribute model) {
		IDynamicAttribute o = new SimpleAttribute(model.getName(), model.getValue());
		logger.debug("Create Attribute: " + o);
		return o;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private List<String> read(List<TReference> model) {
		List<String> out = new ArrayList<String>();
		for (TReference ref : model) {
			out.add(ref.getRefId());
		}
		return out;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private Workflow read(TAction model) {
		return new Workflow(model.getId(), model.getDescription(), read(model.getConditions()), read(model.getWorkflow().getTask()));
	}

	/**
	 * 
	 * @param configurable
	 * @param properties
	 */
	private void configure(IConfigurable configurable, List<TProperty> properties) {
		if (properties != null) {
			for (TProperty property : properties) {
				configurable.setProperty(property.getName(), property.getValue());
			}
		}
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private TaskDescription read(TTask model) {
		TaskDescription out = new TaskDescription(model.getId(), model.getClassname());
		configure(out, model.getProperty());
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
	 * @see org.essembeh.rtfm.core.configuration.io.ICoreConfigurationProvider#getTasks()
	 */
	@Override
	public List<TaskDescription> getTasks() {
		List<TaskDescription> out = new ArrayList<TaskDescription>();
		if (modelAvailable()) {
			for (TTask taskModel : getModel().getTasks().getTask()) {
				TaskDescription task = read(taskModel);
				logger.debug("Found task: " + task);
				out.add(task);
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
			for (TAction actionModel : getModel().getActions().getAction()) {
				Workflow action = read(actionModel);
				logger.debug("Found workflow: " + action);
				out.add(action);
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
