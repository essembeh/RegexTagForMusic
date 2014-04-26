package org.essembeh.rtfm.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.exception.MissingTaskException;
import org.essembeh.rtfm.app.filehandler.FileHandler;
import org.essembeh.rtfm.app.filehandler.attribute.IAttributeGenerator;
import org.essembeh.rtfm.app.filehandler.attribute.RegexAttributeGenerator;
import org.essembeh.rtfm.app.filehandler.attribute.SimpleAttributeGenerator;
import org.essembeh.rtfm.app.utils.IConfigurable;
import org.essembeh.rtfm.app.utils.StringSubstitutor;
import org.essembeh.rtfm.app.workflow.impl.CustomTaskDescription;
import org.essembeh.rtfm.app.workflow.impl.TaskDescription;
import org.essembeh.rtfm.app.workflow.impl.Workflow;
import org.essembeh.rtfm.fs.condition.AndCondition;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.condition.MultipleCondition;
import org.essembeh.rtfm.fs.condition.OrCondition;
import org.essembeh.rtfm.fs.condition.impl.AlwaysFalse;
import org.essembeh.rtfm.fs.condition.impl.AlwaysTrue;
import org.essembeh.rtfm.fs.condition.impl.AttributeExists;
import org.essembeh.rtfm.fs.condition.impl.AttributeValueEquals;
import org.essembeh.rtfm.fs.condition.impl.AttributeValueMatches;
import org.essembeh.rtfm.fs.condition.impl.Extension;
import org.essembeh.rtfm.fs.condition.impl.FileOrFolder;
import org.essembeh.rtfm.fs.condition.impl.FileOrFolder.ResourceType;
import org.essembeh.rtfm.fs.condition.impl.Not;
import org.essembeh.rtfm.fs.condition.impl.VirtualPathMatches;
import org.essembeh.rtfm.model.configuration.TConditionAttributeExists;
import org.essembeh.rtfm.model.configuration.TConditionAttributeValueEquals;
import org.essembeh.rtfm.model.configuration.TConditionAttributeValueMatches;
import org.essembeh.rtfm.model.configuration.TConditionBase;
import org.essembeh.rtfm.model.configuration.TConditionExtension;
import org.essembeh.rtfm.model.configuration.TConditionFalse;
import org.essembeh.rtfm.model.configuration.TConditionFileOrFolder;
import org.essembeh.rtfm.model.configuration.TConditionGroup;
import org.essembeh.rtfm.model.configuration.TConditionTrue;
import org.essembeh.rtfm.model.configuration.TConditionVirtualPathMatches;
import org.essembeh.rtfm.model.configuration.TConfiguration;
import org.essembeh.rtfm.model.configuration.TFileHandler;
import org.essembeh.rtfm.model.configuration.TFixedAttribute;
import org.essembeh.rtfm.model.configuration.TGroupLogic;
import org.essembeh.rtfm.model.configuration.TProperty;
import org.essembeh.rtfm.model.configuration.TRegexAttribute;
import org.essembeh.rtfm.model.configuration.TSubstitution;
import org.essembeh.rtfm.model.configuration.TTask;
import org.essembeh.rtfm.model.configuration.TTaskRef;
import org.essembeh.rtfm.model.configuration.TWorkflow;

public class ConfigurationReader {
	// Log4j logger
	private final Logger logger = Logger.getLogger(ConfigurationReader.class);

	/**
	 * Attributes
	 */
	private final TConfiguration model;
	private final StringSubstitutor stringSubstitutor;

	public ConfigurationReader(TConfiguration model) {
		this.model = model;
		this.stringSubstitutor = initSS();
	}

	public List<FileHandler> readFileHandlers() {
		List<FileHandler> out = new ArrayList<>();
		if (model.getFilehandlers() != null) {
			for (TFileHandler fileHandlerModel : model.getFilehandlers().getFilehandler()) {
				FileHandler fileHandler = readFilehandler(fileHandlerModel);
				logger.debug("Found filehandler: " + ToStringBuilder.reflectionToString(fileHandler));
				out.add(fileHandler);
			}
		}
		return out;
	}

	public Map<String, String> readProperties() {
		Map<String, String> out = new HashMap<>();
		if (model.getProperties() != null) {
			for (TProperty p : model.getProperties().getProperty()) {
				out.put(p.getName(), p.getValue());
			}
		}
		return out;
	}

	public List<Workflow> readWorkflows() throws MissingTaskException {
		List<Workflow> out = new ArrayList<>();
		Map<String, TaskDescription> taskDescriptions = new HashMap<>();
		if (model.getTasks() != null) {
			for (TTask taskModel : model.getTasks().getTask()) {
				TaskDescription taskDescription = readTaskDescription(taskModel);
				taskDescriptions.put(taskDescription.getId(), taskDescription);
			}
		}
		if (model.getWorkflows() != null) {
			for (TWorkflow workflowModel : model.getWorkflows().getWorkflow()) {
				Workflow workflow = readWorkflow(workflowModel, taskDescriptions);
				out.add(workflow);
			}
		}
		return out;
	}

	private Workflow readWorkflow(TWorkflow workflowModel, Map<String, TaskDescription> taskDescriptions)
			throws MissingTaskException {
		Workflow out = new Workflow(workflowModel.getId(), workflowModel.getDescription(),
				readCondition(workflowModel.getConditions()), workflowModel.isUser(), workflowModel.isAuto());
		for (TTaskRef taskReference : workflowModel.getTasks().getTask()) {
			TaskDescription taskDescription = taskDescriptions.get(taskReference.getRefId());
			if (taskDescription == null) {
				throw new MissingTaskException(workflowModel.getId(), taskReference.getRefId());
			}
			CustomTaskDescription customTaskDescription = new CustomTaskDescription(taskDescription);
			readConfigurable(customTaskDescription, taskReference.getProperty());
			out.addCustomTaskDescription(customTaskDescription);
		}
		return out;
	}

	private TaskDescription readTaskDescription(TTask taskModel) {
		TaskDescription out = new TaskDescription(taskModel.getId(), taskModel.getClassname());
		readConfigurable(out, taskModel.getProperty());
		return out;
	}

	private StringSubstitutor initSS() {
		StringSubstitutor out = new StringSubstitutor();
		if (model.getSubstitutions() != null) {
			for (TSubstitution sub : model.getSubstitutions().getSubstitution()) {
				String key = sub.getKey();
				String value = sub.getValue();
				out.addSubstitution(key, value);
			}
		}
		return out;
	}

	private FileHandler readFilehandler(TFileHandler model) {
		String id = model.getId();
		FileHandler fileHandler = new FileHandler(id);
		// Get conditions
		fileHandler.setCondition(readCondition(model.getConditions()));
		// Attributes
		if (model.getAttributes() != null) {
			for (TFixedAttribute fixedAttribute : model.getAttributes().getAttribute()) {
				fileHandler.addAttribute(readAttributeGenerator(fixedAttribute));
			}
			for (TRegexAttribute regexAttribute : model.getAttributes().getRegexAttribute()) {
				fileHandler.addAttribute(readAttributeGenerator(regexAttribute));
			}
		}
		return fileHandler;
	}

	private ICondition readCondition(TConditionGroup model) {
		MultipleCondition out = null;
		if (model != null) {
			if (model.getLogic() == TGroupLogic.OR) {
				out = new OrCondition();
			} else {
				out = new AndCondition();
			}
			for (TConditionBase child : model.getTrueOrFalseOrAttributeExists()) {
				if (child instanceof TConditionTrue) {
					out.addCondition(createInverse(new AlwaysTrue(), child));
				} else if (child instanceof TConditionFalse) {
					out.addCondition(createInverse(new AlwaysFalse(), child));
				} else if (child instanceof TConditionAttributeExists) {
					TConditionAttributeExists cond = (TConditionAttributeExists) child;
					out.addCondition(createInverse(new AttributeExists(cond.getName(), cond.isExists()), child));
				} else if (child instanceof TConditionAttributeValueEquals) {
					TConditionAttributeValueEquals cond = (TConditionAttributeValueEquals) child;
					out.addCondition(createInverse(new AttributeValueEquals(cond.getName(), cond.getValue()), child));
				} else if (child instanceof TConditionAttributeValueMatches) {
					TConditionAttributeValueMatches cond = (TConditionAttributeValueMatches) child;
					out.addCondition(createInverse(new AttributeValueMatches(cond.getName(), ss(cond.getPattern())),
							child));
				} else if (child instanceof TConditionExtension) {
					TConditionExtension cond = (TConditionExtension) child;
					String[] extensions = cond.getList().split(" ");
					out.addCondition(createInverse(new Extension(extensions, cond.isCaseSensitive()), child));
				} else if (child instanceof TConditionFileOrFolder) {
					TConditionFileOrFolder cond = (TConditionFileOrFolder) child;
					out.addCondition(createInverse(new FileOrFolder(ResourceType.valueOf(cond.getType())), child));
				} else if (child instanceof TConditionVirtualPathMatches) {
					TConditionVirtualPathMatches childObject = (TConditionVirtualPathMatches) child;
					out.addCondition(createInverse(new VirtualPathMatches(ss(childObject.getPattern())), child));
				} else if (child instanceof TConditionGroup) {
					TConditionGroup cond = (TConditionGroup) child;
					out.addCondition(createInverse(readCondition(cond), child));
				}
			}
		}
		return out;
	}

	private ICondition createInverse(ICondition c, TConditionBase model) {
		return model.isInverse() ? new Not(c) : c;
	}

	private IAttributeGenerator readAttributeGenerator(TRegexAttribute model) {
		IAttributeGenerator o = new RegexAttributeGenerator(model.getName(), ss(model.getPattern()), model.getGroup(),
				model.isOptional());
		return o;
	}

	private IAttributeGenerator readAttributeGenerator(TFixedAttribute model) {
		IAttributeGenerator o = new SimpleAttributeGenerator(model.getName(), model.getValue());
		return o;
	}

	private Pattern ss(String input) {
		return Pattern.compile(stringSubstitutor == null ? input : stringSubstitutor.apply(input));
	}

	private void readConfigurable(IConfigurable configurable, List<TProperty> properties) {
		if (properties != null) {
			for (TProperty p : properties) {
				configurable.setProperty(p.getName(), p.getValue());
			}
		}
	}
}
