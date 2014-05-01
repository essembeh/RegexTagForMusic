package org.essembeh.rtfm.fs.io;

import java.io.File;

public class NoHiddenFilesScannerExtension extends DefaultScannerExtension implements IScannerExtension {
	private final boolean skipHiddenFiles;

	public NoHiddenFilesScannerExtension(boolean skipHiddenFiles) {
		this.skipHiddenFiles = skipHiddenFiles;
	}

	@Override
	public boolean skipResource(File file) {
		return skipHiddenFiles && file.isHidden();
	}
}
