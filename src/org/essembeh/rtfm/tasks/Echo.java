package org.essembeh.rtfm.tasks;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.IRTFMTask;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class Echo implements IRTFMTask {

	private static final Logger logger = Logger.getLogger(Echo.class);

	List<String> messages;

	public Echo() {
		messages = new ArrayList<String>();
	}

	@Override
	public void setProperty(String name, String value) {
		messages.add(name + "=" + value);
	}

	@Override
	public void execute(IMusicFile file) {
		for (String message : messages) {
			logger.info(message);
		}
	}
}
