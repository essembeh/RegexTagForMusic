package org.essembeh.rtfm.fs.content.interfaces;

import java.util.List;

import org.essembeh.rtfm.fs.content.VirtualPath;

public interface IProject {

	String getName();

	void setName(String name);

	IFolder getRootFolder();

	IResource findResource(VirtualPath virtualPath);

	String getScanDate();

	List<IResource> getNewResources();
}
