package org.essembeh.rtfm.fs.content;

import java.io.File;

import org.essembeh.rtfm.fs.content.interfaces.IFile;

public class FileImpl extends ResourceImpl implements IFile {

	public FileImpl(File file) {
		super(file);
	}

}
