package org.essembeh.rtfm.fs.content.interfaces;

import org.essembeh.rtfm.fs.content.VirtualPath;

public interface IProject {

	String getName();

	void setName(String name);

	IFolder getRootFolder();

	IResource findResource(VirtualPath virtualPath);
}
