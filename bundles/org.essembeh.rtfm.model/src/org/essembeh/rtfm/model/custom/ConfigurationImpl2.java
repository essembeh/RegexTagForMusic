package org.essembeh.rtfm.model.custom;

import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.model.rtfm.impl.ConfigurationImpl;

public class ConfigurationImpl2 extends ConfigurationImpl {
	@Override
	public String resolvePattern(String in) {
		String out = in;
		for (Entry<String, String> s : getSubstitutions().entrySet()) {
			out = StringUtils.replace(out, s.getKey(), s.getValue());
		}
		return out;
	}
}
