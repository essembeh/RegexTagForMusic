package org.essembeh.rtfm.fs.condition.impl;

import java.util.regex.Pattern;

import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class VirtualPathMatches implements ICondition {

	private final Pattern regexOnPath;

	public VirtualPathMatches(Pattern regexOnPath) {
		this.regexOnPath = regexOnPath;
	}

	@Override
	public boolean isTrue(IResource resource) {
		return regexOnPath.matcher(resource.getVirtualPath().toString()).matches();
	}
}
