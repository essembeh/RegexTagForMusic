package org.essembeh.rtfm.core.library.listener;

import org.essembeh.rtfm.core.library.file.FileType;
import org.essembeh.rtfm.core.library.file.IMusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public class SysoutLibraryListener implements ILibraryListener {

	@Override
	public void errorMatchingDynamicAttribute(VirtualFile file, String comment) {
		System.out.println("Event> Error with dynamic attribute, file:" + file + ", message:" + comment);

	}

	@Override
	public void noFileHandlerForFile(VirtualFile file) {
		System.out.println("Event> No file handler found for file:" + file);
	}

	@Override
	public void loadLibraryNewFile(IMusicFile musicFile) {
		System.out.println("Event> New file in Library: " + musicFile);
	}

	@Override
	public void loadLibraryFileRemoved(String virtualpath, FileType fileType) {
		System.out.println("Event> Old file no more present in Library, type:" + fileType + ", path:" + virtualpath);
	}

}
