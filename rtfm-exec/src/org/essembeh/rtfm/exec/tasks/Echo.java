package org.essembeh.rtfm.exec.tasks;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.app.utils.AbstractConfigurable;
import org.essembeh.rtfm.app.workflow.IExecutable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class Echo extends AbstractConfigurable implements IExecutable {

	private static final Logger logger = Logger.getLogger(Echo.class);

	@Override
	public int execute(IResource resource) {
		for (Pair<String, String> property : getProperties()) {
			logger.info(property.getKey() + " = " + property.getValue());
		}
		return 0;
	}
}
