package org.essembeh.rtfm.fs.exception;

import org.essembeh.rtfm.fs.content.FolderImpl;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ResourceAlreadyExistsException extends FileSystemException {

	private static final long serialVersionUID = -6942192918888204615L;

	public ResourceAlreadyExistsException(IResource resource, FolderImpl folderImpl) {
		super(String.format("Folder '%s' already contains a resource with name: ", folderImpl, resource.getName()));
	}

}
