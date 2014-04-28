package org.essembeh.rtfm.exec.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.exception.ExecutionException;
import org.essembeh.rtfm.core.utils.Configurable;
import org.essembeh.rtfm.core.workflow.IExecutable;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public abstract class CommonExecutable extends Configurable implements IExecutable {

	static protected Logger logger = Logger.getLogger(CommonExecutable.class);

	public final static String RC_KEY = "rc";

	public CommonExecutable() {
	}

	@Override
	final public int execute(IResource resource) throws ExecutionException {
		int out = execute0(resource);
		String userReturnCode = getFirstProperty(RC_KEY);
		if (userReturnCode != null && StringUtils.isNumeric(userReturnCode)) {
			int userRc = Integer.parseInt(userReturnCode);
			logger.info("User force return: " + userRc + ", original return was: " + out);
			out = userRc;
		}
		return out;
	}

	protected abstract int execute0(IResource resource) throws ExecutionException;
}
