package org.essembeh.rtfm.fs.content.interfaces;

import java.io.File;

import org.essembeh.rtfm.fs.content.Attributes;
import org.essembeh.rtfm.fs.content.VirtualPath;

public interface IResource {

	File getFile();

	VirtualPath getVirtualPath();

	Attributes getAttributes();

	IFolder getParentFolder();

	String getName();

	void setParentFolder(IFolder parentFolder);
}
