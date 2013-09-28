package org.essembeh.rtfm.fs.content;

import java.io.File;

import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ProjectImpl extends FolderImpl implements IProject {
	public ProjectImpl(File rootFolder) {
		super(rootFolder, VirtualPath.ROOT);
	}

	@Override
	public IResource findResource(VirtualPath virtualPath) {
		throw new IllegalStateException("Not implemented");
	}
}
