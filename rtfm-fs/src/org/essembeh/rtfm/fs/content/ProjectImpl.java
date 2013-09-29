package org.essembeh.rtfm.fs.content;

import java.io.File;

import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ProjectImpl implements IProject {

	private final IFolder rootFolder;
	private String name;

	public ProjectImpl(File rootFolder) {
		this(rootFolder, rootFolder.getName());
	}

	public ProjectImpl(File rootFolder, String name) {
		this.rootFolder = new FolderImpl(rootFolder, VirtualPath.ROOT);
		this.name = name;
	}

	@Override
	public IResource findResource(VirtualPath virtualPath) {
		throw new IllegalStateException("Not implemented");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IFolder getRootFolder() {
		return rootFolder;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
