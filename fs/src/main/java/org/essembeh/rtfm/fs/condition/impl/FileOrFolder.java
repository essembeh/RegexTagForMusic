package org.essembeh.rtfm.fs.condition.impl;

import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IFile;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class FileOrFolder implements ICondition {
	public enum ResourceType {
		FILE, FOLDER
	};

	private final ResourceType type;

	public FileOrFolder(ResourceType type) {
		this.type = type;
	}

	@Override
	public boolean isTrue(IResource resource) {
		return (type == ResourceType.FILE && resource instanceof IFile) || (type == ResourceType.FOLDER && resource instanceof IFolder);
	}

}
