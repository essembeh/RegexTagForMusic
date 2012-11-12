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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.Task;
import org.essembeh.rtfm.core.actions.Workflow;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.filehandler.FileHandler.Logic;
import org.essembeh.rtfm.core.filehandler.RegexOnPathCondition;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.filehandler.dynamic.RegexAttribute;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
import org.essembeh.rtfm.core.utils.identifiers.TaskIdentifier;
import org.essembeh.rtfm.core.utils.identifiers.WorkflowIdentifier;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
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

public class CoreConfigurationLoaderV1 implements ICoreConfigurationLoader {
	/**
	 * Attributes
	 */
	private final static Logic DEFAULT_LOGIC = Logic.AND;
	private static final Logger logger = Logger.getLogger(CoreConfigurationLoaderV1.class);
	private TCoreConfigurationV1 model;

	/**
	 * Constructor
	 */
	@Inject
	public CoreConfigurationLoaderV1() {
		this.model = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.io.ICoreConfigurationLoader#
	 * loadConfiguration(java.io.InputStream)
	 */
	@Override
	public void loadConfiguration(InputStream input) throws ConfigurationException {
		model = null;
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.configuration.core.version1");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
			JAXBElement<TCoreConfigurationV1> root = unmarshaller.unmarshal(new StreamSource(input),
					TCoreConfigurationV1.class);
			model = root.getValue();
		} catch (JAXBException e) {
			logger.info("Cannot load core configuration version 1: " + e.getMessage());
			throw new ConfigurationException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.essembeh.rtfm.core.configuration.io.ICoreConfigurationLoader#
	 * getFileHandlers()
	 */
	@Override
	public List<FileHandler> getFileHandlers() throws ConfigurationException {
		if (model == null) {
			throw new ConfigurationException("Model is null");
		}
		List<FileHandler> out = new ArrayList<FileHandler>();
		for (TFileHandler fileHandlerModel : model.getFilehandlers().getFilehandler()) {
			FileHandler theFileHandler = read(fileHandlerModel);
			out.add(theFileHandler);
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
		FileHandler fileHandler = new FileHandler(id, DEFAULT_LOGIC);
		// Conditions
		if (model.getConditions() != null) {
			for (TConditionOnVirtualPath virtualPathModel : model.getConditions().getVirtualpath()) {
				fileHandler.addCondition(new RegexOnPathCondition(virtualPathModel.getMatches()));
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
		IDynamicAttribute o = new RegexAttribute(model.getName(), model.isHidden(),
				Pattern.compile(model.getPattern()), model.getGroup(), model.isOptional());
		logger.debug("Create DynamicAttribute: " + o);
		return o;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	private Attribute read(TFixedAttribute model) {
		Attribute o = new Attribute(model.getName(), model.getValue(), model.isHidden());
		logger.debug("Create Attribute: " + o);
		return o;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.essembeh.rtfm.core.configuration.io.ICoreConfigurationLoader#getWorkflows
	 * ()
	 */
	@Override
	public IdList<Workflow, Identifier<Workflow>> getWorkflows() throws ConfigurationException {
		if (model == null) {
			throw new ConfigurationException("Model is null");
		}
		IdList<Workflow, Identifier<Workflow>> list = new IdList<Workflow, Identifier<Workflow>>(
				new WorkflowIdentifier());
		IdList<Task, TaskIdentifier> taskList = new IdList<Task, TaskIdentifier>(new TaskIdentifier());
		for (TTask taskModel : model.getTasks().getTask()) {
			Task task = read(taskModel);
			logger.debug("Found task: " + task);
			taskList.add(task);
		}

		for (TAction actionModel : model.getActions().getAction()) {
			Workflow action = read(actionModel);
			list.add(action);
			// Tasks
			for (TReference taskRef : actionModel.getWorkflow().getTask()) {
				Task taskExecutor = taskList.get(taskRef.getRefId());
				if (taskExecutor == null) {
					throw new ConfigurationException("Cannot find task: " + taskRef.getRefId());
				}
				action.addTask(taskExecutor);
			}
			logger.debug("Found action: " + action);
		}
		return list;
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

}
