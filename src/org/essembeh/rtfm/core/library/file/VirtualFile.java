package org.essembeh.rtfm.core.library.file;

import java.io.File;

import org.essembeh.rtfm.core.utils.FileUtils;

public class VirtualFile extends File {

	private static final long serialVersionUID = 2859877446763137741L;

	String virtualPath;

	public VirtualFile(File file, File parent) {
		super(file.getAbsolutePath());
		this.virtualPath = FileUtils.extractRelativePath(file, parent);
	}

	public String getVirtualPath() {
		return virtualPath;
	}

	@Override
	public String toString() {
		return virtualPath;
	}

}
