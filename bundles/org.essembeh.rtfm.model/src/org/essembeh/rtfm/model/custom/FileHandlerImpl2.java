package org.essembeh.rtfm.model.custom;

import java.util.regex.Pattern;

import org.essembeh.rtfm.model.rtfm.impl.FileHandlerImpl;

public class FileHandlerImpl2 extends FileHandlerImpl {

	@Override
	public boolean accept(String virtualPath) {
		String regex = getPattern();
		if (getConfiguration() != null) {
			regex = getConfiguration().resolvePattern(regex);
		}
		return Pattern.matches(regex, virtualPath);
	}

	@Override
	public String toString() {
		return getName();
	}
}
