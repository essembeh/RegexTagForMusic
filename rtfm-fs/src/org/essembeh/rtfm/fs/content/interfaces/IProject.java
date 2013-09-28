package org.essembeh.rtfm.fs.content.interfaces;

import org.essembeh.rtfm.fs.content.VirtualPath;

public interface IProject extends IFolder {

	IResource findResource(VirtualPath virtualPath);
}
