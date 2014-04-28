package org.essembeh.rtfm.exec.tasks;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.workflow.IExecutable;
import org.essembeh.rtfm.exec.utils.CommonExecutable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class Echo extends CommonExecutable implements IExecutable {

	private static final Logger logger = Logger.getLogger(Echo.class);

	@Override
	public int execute0(IResource resource) {
		for (Pair<String, String> property : getProperties()) {
			logger.info(property.getKey() + " = " + property.getValue());
		}
		return 0;
	}
}
