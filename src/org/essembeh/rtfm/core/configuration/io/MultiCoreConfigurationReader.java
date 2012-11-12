package org.essembeh.rtfm.core.configuration.io;

import org.essembeh.rtfm.core.configuration.CoreConfiguration;
import org.essembeh.rtfm.core.utils.version.MultiObjectReader;

import com.google.inject.Inject;

public class MultiCoreConfigurationReader extends MultiObjectReader<CoreConfiguration> {

	/**
	 * 
	 */
	@Inject
	public MultiCoreConfigurationReader(CoreConfigurationLoaderV2 version2, CoreConfigurationLoaderV1 version1) {
		addreader(version2);
		addreader(version1);
	}
}
