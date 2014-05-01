package org.essembeh.rtfm.exec.tasks;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.workflow.IExecutable;
import org.essembeh.rtfm.exec.utils.CommonExecutable;
import org.essembeh.rtfm.exec.utils.PropertyUtils;
import org.essembeh.rtfm.fs.content.interfaces.IResource;
import org.essembeh.rtfm.fs.util.AttributesHelper;

public class AttributeTool extends CommonExecutable implements IExecutable {
	private static final Logger logger = Logger.getLogger(AttributeTool.class);

	public final static String CREATE = "create";
	public final static String UPDATE = "update";
	public final static String REMOVE = "remove";

	@Override
	public int execute0(IResource resource) {
		for (Pair<String, String> p : getProperties()) {
			if (CREATE.equals(p.getKey())) {
				Pair<String, String> pair = PropertyUtils.stringToPair(p.getValue());
				String attributeName = pair.getKey();
				String attributeValue = PropertyUtils.valuateDynamicEnvironmentVariable(pair.getValue(), resource);
				logger.debug(String.format("Create attribute: %s = %s", attributeName, attributeValue));
				AttributesHelper.set(resource, attributeName, attributeValue);
			} else if (UPDATE.equals(p.getKey())) {
				Pair<String, String> pair = PropertyUtils.stringToPair(p.getValue());
				String attributeName = pair.getKey();
				String attributeValue = PropertyUtils.valuateDynamicEnvironmentVariable(pair.getValue(), resource);
				if (resource.getAttributes().containsKey(attributeName)) {
					logger.debug(String.format("Update attribute: %s = %s", attributeName, attributeValue));
					AttributesHelper.set(resource, attributeName, attributeValue);
				} else {
					logger.debug(String.format("Cannot update missing attribute: %s = %s", attributeName,
							attributeValue));
				}
			} else if (REMOVE.equals(p.getKey())) {
				String attributeName = p.getValue();
				if (resource.getAttributes().containsKey(attributeName)) {
					logger.debug(String.format("Remove attribute: %s", attributeName));
					AttributesHelper.delete(resource, attributeName);
				} else {
					logger.debug(String.format("Cannot remove missing attribute: %s", attributeName));
				}
			}
		}
		return 0;
	}
}
