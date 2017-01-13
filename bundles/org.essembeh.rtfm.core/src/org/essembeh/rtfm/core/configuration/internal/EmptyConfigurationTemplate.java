package org.essembeh.rtfm.core.configuration.internal;

import org.essembeh.rtfm.core.configuration.IConfigurationTemplate;
import org.essembeh.rtfm.model.RtfmCustomFactory;
import org.essembeh.rtfm.model.rtfm.Configuration;

public class EmptyConfigurationTemplate implements IConfigurationTemplate {

	public static final String ID = "default";

	@Override
	public String getName() {
		return "Empty connfiguration";
	}

	@Override
	public Configuration createModel() {
		Configuration out = RtfmCustomFactory.eINSTANCE.createConfiguration();
		return out;
	}

	@Override
	public String getId() {
		return ID;
	}

}
