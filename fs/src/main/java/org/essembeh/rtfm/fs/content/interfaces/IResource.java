package org.essembeh.rtfm.fs.content.interfaces;

import java.io.File;
import java.util.Map;

import org.essembeh.rtfm.fs.content.VirtualPath;

public interface IResource extends Comparable<IResource> {

	File getFile();

	VirtualPath getVirtualPath();

	IFolder getParentFolder();

	String getName();

	void setParentFolder(IFolder parentFolder);

	Map<String, String> getAttributes();
}
