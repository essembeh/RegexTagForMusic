package org.essembeh.rtfm.core.filehandler;

import org.essembeh.rtfm.core.exception.DynamicAttributeException;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.core.library.file.VirtualFile;

public interface FileHandler {

	String getId();

	boolean canHandle(VirtualFile file);

	MusicFile create(VirtualFile file) throws InstantiationException, DynamicAttributeException;

}
