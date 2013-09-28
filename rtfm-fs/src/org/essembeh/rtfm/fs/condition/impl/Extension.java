package org.essembeh.rtfm.fs.condition.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class Extension implements ICondition {

	private final List<String> extensions;
	private final boolean caseSensitive;

	public Extension(String[] extensionsList, boolean caseSensitive) {
		this.extensions = new ArrayList<String>();
		this.caseSensitive = caseSensitive;
		for (String ext : extensionsList) {
			extensions.add(caseSensitive ? ext : ext.toLowerCase());
		}
	}

	@Override
	public boolean isTrue(IResource resource) {
		String extension = FilenameUtils.getExtension(resource.getName());
		return extensions.contains(caseSensitive ? extension : extension.toLowerCase());
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
