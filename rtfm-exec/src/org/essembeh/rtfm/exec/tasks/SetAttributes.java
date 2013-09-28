package org.essembeh.rtfm.exec.tasks;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.utils.AbstractConfigurable;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class SetAttributes extends AbstractConfigurable implements IExecutable {
	private static final Logger logger = Logger.getLogger(SetAttributes.class);

	@Override
	public void execute(IResource resource) {
		for (Pair<String, String> e : getProperties()) {
			if (resource.getAttributes().contains(e.getKey())) {
				logger.debug("Update attribute: " + e.getKey() + " = " + e.getValue());
			} else {
				logger.debug("Create attribute: " + e.getKey() + " = " + e.getValue());
			}
			resource.getAttributes().setValue(e.getKey(), e.getValue());
		}
	}
}
