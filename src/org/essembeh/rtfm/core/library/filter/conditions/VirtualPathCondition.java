package org.essembeh.rtfm.core.library.filter.conditions;

import java.util.regex.Pattern;

import org.essembeh.rtfm.core.library.file.MusicFile;

public class VirtualPathCondition implements Condition {

	Pattern regexOnPath;

	public VirtualPathCondition(Pattern regexOnPath) {
		this.regexOnPath = regexOnPath;
	}

	@Override
	public boolean isTrue(MusicFile musicFile) {
		return regexOnPath.matcher(musicFile.getVirtualPath()).matches();
	}

	@Override
	public String toString() {
		return "VirtualPathCondition [regexOnPath=" + regexOnPath + "]";
	}

}
