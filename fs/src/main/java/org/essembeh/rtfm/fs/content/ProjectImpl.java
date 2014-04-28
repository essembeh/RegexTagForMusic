package org.essembeh.rtfm.fs.content;

import java.io.File;
import java.util.List;

import org.essembeh.rtfm.fs.condition.impl.AttributeValueEquals;
import org.essembeh.rtfm.fs.content.interfaces.IFolder;
import org.essembeh.rtfm.fs.content.interfaces.IProject;
import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class ProjectImpl implements IProject {

	private final IFolder rootFolder;
	private String name;
	private String scanDate;

	public ProjectImpl(File rootFolder, String scanDate) {
		this(rootFolder, rootFolder.getName(), scanDate);
	}

	public ProjectImpl(File rootFolder, String name, String scanDate) {
		this.rootFolder = new FolderImpl(rootFolder, VirtualPath.ROOT);
		this.name = name;
		this.scanDate = scanDate;
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

	@Override
	public String getScanDate() {
		return scanDate;
	}

	@Override
	public List<IResource> getNewResources() {
		return rootFolder.getFilteredResources(new AttributeValueEquals(Attributes.DATE_KEY, scanDate));
	}
}
