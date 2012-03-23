package org.essembeh.rtfm.core.filehandler;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.library.file.VirtualFile;

public class RegexOnPathCondition implements ICondition {

	private Pattern pattern;

	public RegexOnPathCondition(String regex) {
		pattern = Pattern.compile(regex);
	}

	@Override
	public boolean isValid(VirtualFile file) {
		return pattern.matcher(file.getVirtualPath()).matches();
	}

}
