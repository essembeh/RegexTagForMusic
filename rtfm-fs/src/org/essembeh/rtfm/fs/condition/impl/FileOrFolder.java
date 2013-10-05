package org.essembeh.rtfm.fs.condition.impl;

import org.essembeh.rtfm.fs.condition.ICondition;
import org.essembeh.rtfm.fs.content.interfaces.IFile;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class FileOrFolder implements ICondition {
	public enum InodeType {
		FILE, FOLDER
	};

	private final InodeType type;

	public FileOrFolder(InodeType type) {
		this.type = type;
	}

	@Override
	public boolean isTrue(IResource resource) {
		return (type == InodeType.FILE && resource instanceof IFile) || (type == InodeType.FOLDER && resource instanceof IFolder);
	}

}
