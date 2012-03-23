package org.essembeh.rtfm.core.filehandler;

import org.essembeh.rtfm.core.library.file.VirtualFile;

public interface ICondition {

	boolean isValid(VirtualFile file);
}
