package org.essembeh.rtfm.app.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBElement;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.exception.UnknownTaskException;
import org.essembeh.rtfm.app.filehandler.FileHandler;
import org.essembeh.rtfm.app.filehandler.attribute.IAttributeGenerator;
import org.essembeh.rtfm.app.filehandler.attribute.RegexAttributeGenerator;
import org.essembeh.rtfm.app.filehandler.attribute.SimpleAttributeGenerator;
import org.essembeh.rtfm.app.utils.StringSubstitutor;
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
import org.essembeh.rtfm.fs.condition.impl.VirtualPathMatches;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionAttributeExists;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionAttributeValueEquals;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionAttributeValueMatches;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionExtension;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionFalse;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionFileOrFolder;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionGroup;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionTrue;
import org.essembeh.rtfm.model.gen.configuration.v1.TConditionVirtualPathMatches;
import org.essembeh.rtfm.model.gen.configuration.v1.TConfiguration;
import org.essembeh.rtfm.model.gen.configuration.v1.TFileHandler;
import org.essembeh.rtfm.model.gen.configuration.v1.TFixedAttribute;
import org.essembeh.rtfm.model.gen.configuration.v1.TGroupLogic;
import org.essembeh.rtfm.model.gen.configuration.v1.TProperty;
import org.essembeh.rtfm.model.gen.configuration.v1.TReference;
import org.essembeh.rtfm.model.gen.configuration.v1.TRegexAttribute;
import org.essembeh.rtfm.model.gen.configuration.v1.TSubstitution;
import org.essembeh.rtfm.model.gen.configuration.v1.TTask;
import org.essembeh.rtfm.model.gen.configuration.v1.TWorkflow;

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

	public List<Workflow> readWorkflows() throws UnknownTaskException {
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
			throws UnknownTaskException {
		Workflow out = new Workflow(workflowModel.getId(), workflowModel.getDescription(), workflowModel.isUser(),
				workflowModel.isAuto());
		out.setCondition(readCondition(workflowModel.getConditions()));
		for (TReference taskReference : workflowModel.getTasks().getTask()) {
			TaskDescription taskDescription = taskDescriptions.get(taskReference.getRefId());
			if (taskDescription == null) {
				throw new UnknownTaskException(workflowModel.getId(), taskReference.getRefId());
			}
			out.addTaskDescription(taskDescription);
		}
		return out;
	}

	private TaskDescription readTaskDescription(TTask taskModel) {
		TaskDescription out = new TaskDescription(taskModel.getId(), taskModel.getClassname());
		for (TProperty property : taskModel.getProperty()) {
			out.setProperty(property.getName(), property.getValue());
		}
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
			for (JAXBElement<?> sub : model.getTrueOrFalseOrAttributeExists()) {
				Object child = sub.getValue();
				if (child instanceof TConditionTrue) {
					out.addCondition(new AlwaysTrue());
				} else if (child instanceof TConditionFalse) {
					out.addCondition(new AlwaysFalse());
				} else if (child instanceof TConditionAttributeExists) {
					TConditionAttributeExists cond = (TConditionAttributeExists) child;
					out.addCondition(new AttributeExists(cond.getName(), cond.isExists()));
				} else if (child instanceof TConditionAttributeValueEquals) {
					TConditionAttributeValueEquals cond = (TConditionAttributeValueEquals) child;
					out.addCondition(new AttributeValueEquals(cond.getName(), cond.getValue()));
				} else if (child instanceof TConditionAttributeValueMatches) {
					TConditionAttributeValueMatches cond = (TConditionAttributeValueMatches) child;
					out.addCondition(new AttributeValueMatches(cond.getName(), ss(cond.getPattern())));
				} else if (child instanceof TConditionExtension) {
					TConditionExtension cond = (TConditionExtension) child;
					String[] extensions = cond.getList().split(" ");
					out.addCondition(new Extension(extensions, cond.isCaseSensitive()));
				} else if (child instanceof TConditionFileOrFolder) {
					TConditionFileOrFolder cond = (TConditionFileOrFolder) child;
					out.addCondition(new FileOrFolder(ResourceType.valueOf(cond.getType())));
				} else if (child instanceof TConditionVirtualPathMatches) {
					TConditionVirtualPathMatches childObject = (TConditionVirtualPathMatches) child;
					out.addCondition(new VirtualPathMatches(ss(childObject.getPattern())));
				} else if (child instanceof TConditionGroup) {
					TConditionGroup cond = (TConditionGroup) child;
					out.addCondition(readCondition(cond));
				}
			}
		}
		return out;
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
}
