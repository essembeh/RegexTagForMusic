package org.essembeh.rtfm.tasks;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class Echo implements ITask {

	private static final Logger logger = Logger.getLogger(Echo.class);
	private Map<String, String> properties = new HashMap<String, String>();

	@Override
	public void execute(IMusicFile file) {
		for (String key : properties.keySet()) {
			logger.info(key + " = " + properties.get(key));
		}
	}

	@Override
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}
}
