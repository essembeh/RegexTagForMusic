package org.essembeh.rtfm.fs.io;

import java.io.File;

public class NoHiddenFilesScannerExtension extends DefaultScannerExtension implements IScannerExtension {
	@Override
	public boolean skipResource(File file) {
		return !file.isHidden();
	}
}
