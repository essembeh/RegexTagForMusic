package org.essembeh.rtfm.core.configuration.io;

import java.io.FileNotFoundException;
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
import org.essembeh.rtfm.core.actions.Action;
import org.essembeh.rtfm.core.actions.ActionIdentifier;
import org.essembeh.rtfm.core.actions.TaskExecutor;
import org.essembeh.rtfm.core.actions.TaskExecutorIdentifier;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.filehandler.FileHandler;
import org.essembeh.rtfm.core.filehandler.RegexOnPathCondition;
import org.essembeh.rtfm.core.filehandler.dynamic.IDynamicAttribute;
import org.essembeh.rtfm.core.filehandler.dynamic.RegexAttribute;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;
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

	private static final Logger logger = Logger.getLogger(CoreConfigurationLoaderV1.class);

	TCoreConfigurationV1 model = null;

	@Inject
	public CoreConfigurationLoaderV1(InputStream source) throws ConfigurationException, FileNotFoundException {
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.configuration.core.version1");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
			JAXBElement<TCoreConfigurationV1> root = unmarshaller.unmarshal(new StreamSource(source),
					TCoreConfigurationV1.class);
			model = root.getValue();
		} catch (JAXBException e) {
			logger.info("Cannot load core configuration version 1: " + e.getMessage());
			throw new ConfigurationException(e.getMessage());
		}
	}

	@Override
	public List<FileHandler> getFileHandlers() throws ConfigurationException {
		if (model == null) {
			throw new ConfigurationException();
		}
		List<FileHandler> out = new ArrayList<FileHandler>();
		for (TFileHandler fileHandlerModel : model.getFilehandlers().getFilehandler()) {
			FileHandler theFileHandler = read(fileHandlerModel);
			out.add(theFileHandler);
		}
		return out;
	}

	private FileHandler read(TFileHandler model) {
		String id = model.getId();
		FileHandler fileHandler = new FileHandler(id);
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

	private IDynamicAttribute read(TRegexAttribute model) {
		IDynamicAttribute o = new RegexAttribute(model.getName(), model.isHidden(),
				Pattern.compile(model.getPattern()), model.getGroup(), model.isOptional());
		logger.debug("Create DynamicAttribute: " + o);
		return o;
	}

	private Attribute read(TFixedAttribute model) {
		Attribute o = new Attribute(model.getName(), model.getValue(), model.isHidden());
		logger.debug("Create Attribute: " + o);
		return o;
	}

	@Override
	public IdList<Action, Identifier<Action>> getActions() throws ConfigurationException {
		if (model == null) {
			throw new ConfigurationException();
		}
		IdList<Action, Identifier<Action>> list = new IdList<Action, Identifier<Action>>(new ActionIdentifier());
		IdList<TaskExecutor, Identifier<TaskExecutor>> taskList = new IdList<TaskExecutor, Identifier<TaskExecutor>>(
				new TaskExecutorIdentifier());
		for (TTask taskModel : model.getTasks().getTask()) {
			TaskExecutor taskExecutor = read(taskModel);
			logger.debug("Found task: " + taskExecutor);
			taskList.add(taskExecutor);
		}

		for (TAction actionModel : model.getActions().getAction()) {
			Action action = read(actionModel);
			list.add(action);
			// Tasks
			for (TReference taskRef : actionModel.getWorkflow().getTask()) {
				TaskExecutor taskExecutor = taskList.get(taskRef.getRefId());
				if (taskExecutor == null) {
					throw new ConfigurationException("Cannot find task: " + taskRef.getRefId());
				}
				action.addTask(taskExecutor);
			}
			logger.debug("Found action: " + action);
		}
		return list;
	}

	private Action read(TAction model) {
		Action out = new Action(model.getName(), model.getId());
		for (TReference ref : model.getApplyOn().getFilehandler()) {
			out.addSupportedType(ref.getRefId());
		}
		return out;
	}

	private TaskExecutor read(TTask model) throws ConfigurationException {
		TaskExecutor out;
		try {
			String id = model.getId();
			String classname = model.getClassname();
			out = new TaskExecutor(id, classname);
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
