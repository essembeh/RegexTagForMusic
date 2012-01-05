package org.essembeh.rtfm.core.configuration.io;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.helpers.DefaultValidationEventHandler;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.attributes.Attribute;
import org.essembeh.rtfm.core.attributes.dynamic.DynamicAttribute;
import org.essembeh.rtfm.core.attributes.dynamic.RegexAttribute;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.core.exception.TaskException;
import org.essembeh.rtfm.core.filehandler.FileHandlerImpl;
import org.essembeh.rtfm.core.filehandler.FileHandlerImpl.FileType;
import org.essembeh.rtfm.core.utils.list.IdList;
import org.essembeh.rtfm.core.utils.list.Identifier;
import org.essembeh.rtfm.core.workflow.Task;
import org.essembeh.rtfm.core.workflow.TaskUtils;
import org.essembeh.rtfm.model.configuration.core.version1.TAttributeSet;
import org.essembeh.rtfm.model.configuration.core.version1.TCoreConfigurationV1;
import org.essembeh.rtfm.model.configuration.core.version1.TFileHandler;
import org.essembeh.rtfm.model.configuration.core.version1.TFixedAttribute;
import org.essembeh.rtfm.model.configuration.core.version1.TProperty;
import org.essembeh.rtfm.model.configuration.core.version1.TReference;
import org.essembeh.rtfm.model.configuration.core.version1.TRegexAttribute;
import org.essembeh.rtfm.model.configuration.core.version1.TTask;
import org.essembeh.rtfm.model.configuration.core.version1.TWorkflow;

import com.google.inject.Inject;

public class CoreConfigurationLoaderV1 implements CoreConfigurationLoader {

	private static final Logger logger = Logger.getLogger(CoreConfigurationLoaderV1.class);

	TCoreConfigurationV1 model;

	@Inject
	public CoreConfigurationLoaderV1() {
	}

	@Override
	public boolean load(InputStream source) {
		model = null;
		try {
			JAXBContext context = JAXBContext.newInstance("org.essembeh.rtfm.model.configuration.core.version1");
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setEventHandler(new DefaultValidationEventHandler());
			JAXBElement<TCoreConfigurationV1> root = unmarshaller.unmarshal(new StreamSource(source),
					TCoreConfigurationV1.class);
			model = root.getValue();
		} catch (JAXBException e) {
			logger.info("Cannot load core configuration version 1: " + e.getMessage());
		}
		return model != null;
	}

	@Override
	public List<FileHandlerImpl> getFileHandlers() throws ConfigurationException {
		if (model == null) {
			throw new ConfigurationException();
		}
		List<FileHandlerImpl> list = new ArrayList<FileHandlerImpl>();

		Map<String, Task> tasks = new HashMap<String, Task>();
		for (TTask it : model.getTask()) {
			tasks.put(it.getId(), read(it));
		}

		IdList<TAttributeSet, Identifier<TAttributeSet>> attributeSets = new IdList<TAttributeSet, Identifier<TAttributeSet>>(
				new Identifier<TAttributeSet>() {
					@Override
					public String getId(TAttributeSet o) {
						return o.getId();
					}
				}, model.getAttributeset());

		for (TFileHandler fileHandlerModel : model.getFilehandler()) {
			FileHandlerImpl theFileHandler = read(fileHandlerModel);
			// Add attribute sets
			for (TReference ref : fileHandlerModel.getAttributeset()) {
				if (attributeSets.contains(ref.getRefId())) {
					TAttributeSet currentAttributeSet = attributeSets.get(ref.getRefId());
					for (TFixedAttribute attribute : currentAttributeSet.getAttribute()) {
						theFileHandler.addAttribute(read(attribute));
					}
					for (TRegexAttribute attribute : currentAttributeSet.getRegexAttribute()) {
						theFileHandler.addDynamicAttribute(read(attribute));
					}
				} else {
					logger.error("Cannot find attribute set: " + ref.getRefId());
				}
			}
			// Add workflows
			for (TWorkflow workflowModel : fileHandlerModel.getAction()) {
				String workflowName = workflowModel.getName();
				List<Task> listOfTasks = read(workflowModel.getTask(), tasks);
				if (listOfTasks != null) {
					theFileHandler.addWorkflow(workflowName, listOfTasks.toArray(new Task[0]));
				} else {
					logger.error("Cannot create workflow: " + workflowName + ", for FileHandler: "
							+ theFileHandler.getId());
				}
			}
			list.add(theFileHandler);
		}
		return list;
	}

	List<Task> read(List<TReference> model, Map<String, Task> tasks) {
		List<Task> list = new ArrayList<Task>();
		for (TReference ref : model) {
			if (!tasks.containsKey(ref.getRefId())) {
				logger.warn("Invalid task: " + ref.getRefId());
				return null;
			}
			list.add(tasks.get(ref.getRefId()));
		}
		return list;
	}

	FileHandlerImpl read(TFileHandler model) {
		String id = model.getId();
		String regex = model.getMatches();
		String type = model.getType();
		FileHandlerImpl fileHandler = new FileHandlerImpl(id, regex, FileType.valueOf(type));
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

	Task read(TTask model) {
		String classname = model.getClassname();
		Task task = null;
		try {
			task = TaskUtils.createTask(classname);
			// Setting properties
			for (TProperty property : model.getProperty()) {
				logger.debug("Setting property: " + property.getName() + " = " + property.getValue());
				task.setProperty(property.getName(), property.getValue());
			}
		} catch (TaskException e) {
			logger.error("Cannot create Task: " + classname);
		}
		return task;
	}

	DynamicAttribute read(TRegexAttribute model) {
		DynamicAttribute o = new RegexAttribute(model.getName(), model.isHidden(), Pattern.compile(model.getPattern()),
				model.getGroup(), model.isOptional());
		logger.debug("Create DynamicAttribute: " + o);
		return o;
	}

	Attribute read(TFixedAttribute model) {
		Attribute o = new Attribute(model.getName(), model.getValue(), model.isHidden());
		logger.debug("Create Attribute: " + o);
		return o;
	}
}
