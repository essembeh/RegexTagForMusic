/**
 * Copyright 2011 Sebastien M-B <essembeh@gmail.com>
 * 
 * This file is part of RegexTagForMusic.
 * 
 * RegexTagForMusic is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * RegexTagForMusic is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * RegexTagForMusic. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.essembeh.rtfm.gui.worker;

import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.actions.Action;
import org.essembeh.rtfm.core.exception.ActionException;
import org.essembeh.rtfm.core.library.file.IMusicFile;

public class ActionWorker extends SwingWorker<Void, Void> {

	private static Logger logger = Logger.getLogger(ActionWorker.class);

	private final List<IMusicFile> listOfFiles;
	private final Action action;
	private final IActionCallback callback;

	public ActionWorker(Action action, List<IMusicFile> list, IActionCallback actionCallback) {
		this.action = action;
		this.callback = actionCallback;
		this.listOfFiles = list;
	}

	@Override
	protected Void doInBackground() {
		int totalCount = this.listOfFiles.size();
		logger.debug("Doing action: " + action + ", in background on " + totalCount + " files");
		callback.start();
		setProgress(0);
		int currentIndex = 0;
		for (IMusicFile musicFile : listOfFiles) {
			logger.debug("Executing action: " + action + ", on file: " + musicFile);
			if (action.supportType(musicFile.getType())) {
				try {
					action.executeOn(musicFile);
				} catch (ActionException e) {
					callback.error(musicFile, e);
				}
				callback.actionSucceeded(musicFile);
			} else {
				callback.unsupportedFiletype(musicFile);
			}
			setProgress(++currentIndex * 100 / totalCount);
		}
		setProgress(100);
		callback.end();
		return null;
	}
}
