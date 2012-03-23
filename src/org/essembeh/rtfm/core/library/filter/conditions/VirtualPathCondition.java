package org.essembeh.rtfm.core.library.filter.conditions;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.library.file.IMusicFile;

public class VirtualPathCondition implements IFilterCondition {

	Pattern regexOnPath;

	public VirtualPathCondition(Pattern regexOnPath) {
		this.regexOnPath = regexOnPath;
	}

	@Override
	public boolean isTrue(IMusicFile musicFile) {
		return regexOnPath.matcher(musicFile.getVirtualPath()).matches();
	}

	@Override
	public String toString() {
		return "VirtualPathCondition [regexOnPath=" + regexOnPath + "]";
	}

}
