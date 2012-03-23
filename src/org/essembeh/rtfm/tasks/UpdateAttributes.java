package org.essembeh.rtfm.tasks;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.IRTFMTask;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.attributes.Attribute;

public class UpdateAttributes implements IRTFMTask {

	private static final Logger logger = Logger.getLogger(UpdateAttributes.class);

	Map<String, String> attributes = new HashMap<String, String>();

	@Override
	public void setProperty(String name, String value) {
		attributes.put(name, value);

	}

	@Override
	public void execute(IMusicFile file) {
		for (String key : attributes.keySet()) {
			String value = attributes.get(key);
			Attribute attr = file.getAttributeList().get(key);
			if (attr == null) {
				logger.debug("Create attribute: " + key + " = " + value);
				Attribute newAttr = new Attribute(key, value, false);
				file.getAttributeList().add(newAttr);
			} else {
				logger.debug("Set attribute: " + key + " = " + value);
				attr.setValue(value);
			}
		}
	}
}