package org.essembeh.rtfm.gui.worker;

import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public interface IActionCallback {

	void start();

	void actionSucceeded(IMusicFile musicFile);

	void unsupportedFiletype(IMusicFile musicFile);

	void error(IMusicFile musicFile, ActionException exception);

	void end();

}
