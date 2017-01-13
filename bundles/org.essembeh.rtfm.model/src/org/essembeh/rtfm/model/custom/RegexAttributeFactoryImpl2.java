package org.essembeh.rtfm.model.custom;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.essembeh.rtfm.model.rtfm.impl.RegexAttributeFactoryImpl;

public class RegexAttributeFactoryImpl2 extends RegexAttributeFactoryImpl {

	@Override
	public String getAttributeValue(String virtualPath) {
		String regex = getPattern();
		if (StringUtils.isEmpty(regex)) {
			regex = getFileHandler().getPattern();
		}
		if (getFileHandler() != null && getFileHandler().getConfiguration() != null) {
			regex = getFileHandler().getConfiguration().resolvePattern(regex);
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(virtualPath);
		if (!m.matches()) {
			String message = MessageFormat.format("Virtualpath {0} does not match pattern {1}", virtualPath, p.pattern());
			throw new IllegalStateException(message);
		}
		if (getGroup() > m.groupCount()) {
			String message = MessageFormat.format("No group {0} for pattern {1} on virtualpath {2}", getGroup(), p.pattern(), virtualPath);
			throw new IllegalStateException(message);
		}
		return m.group(getGroup());
	}

	@Override
	public String toString() {
		return getName();
	}
}
