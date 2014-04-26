package org.essembeh.rtfm.fs.io;

import java.io.File;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public interface IScannerExtension {

	boolean skipResource(File file);

	void postCreation(IResource resource);
}
