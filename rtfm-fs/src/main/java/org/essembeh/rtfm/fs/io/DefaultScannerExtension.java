package org.essembeh.rtfm.fs.io;

import java.io.File;

import org.essembeh.rtfm.fs.content.interfaces.IResource;

public class DefaultScannerExtension implements IScannerExtension {

	@Override
	public boolean skipResource(File file) {
		return false;
	}

	@Override
	public void postCreation(IResource resource) {
	}

}
