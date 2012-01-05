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

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.file.MusicFile;
import org.essembeh.rtfm.gui.controller.GuiController;
import org.essembeh.rtfm.gui.panel.StatusBar;

public class ActionWorker extends SwingWorker<Void, Void> {

	private static Logger logger = Logger.getLogger(ActionWorker.class);

	List<MusicFile> listOfFiles = null;

	String action;

	StatusBar statusBar = null;

	GuiController controller;

	public ActionWorker(GuiController controller, List<MusicFile> list, String action) {
		this.controller = controller;
		this.action = action;
		this.listOfFiles = new ArrayList<MusicFile>();
		for (MusicFile musicFile : list) {
			if (musicFile.getAllActions().contains(action)) {
				listOfFiles.add(musicFile);
			}
		}
	}

	@Override
	protected Void doInBackground() {
		int totalCount = this.listOfFiles.size();
		logger.debug("Doing action: " + action + ", in background on " + totalCount + " files");
		int errorCount = 0;
		setProgress(0);
		int currentIndex = 0;
		for (MusicFile file : listOfFiles) {
			logger.debug("Executing action: " + action + ", on file: " + file);
			try {
				file.executeAction(action);
			} catch (Exception e) {
				errorCount++;
				logger.error("Error on file: " + file, e);
			}
			setProgress(++currentIndex * 100 / totalCount);
		}
		setProgress(100);
		String message = "Action \"" + action + "\" executed on " + totalCount + " file" + (totalCount > 1 ? "s" : "");
		if (errorCount == 0) {
			controller.displayStatusMessage(message, false);
		} else {
			String errorMessage = " with " + errorCount + " error" + (errorCount > 1 ? "s" : "");
			controller.displayStatusMessage(message + errorMessage, true);
		}
		controller.updateCurrentTab();
		return null;
	}
}
