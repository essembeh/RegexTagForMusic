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

import org.essembeh.rtfm.core.utils.StringUtils;
import org.essembeh.rtfm.gui.panel.StatusBar;
import org.essembeh.rtfm.interfaces.IMusicFile;

public class TagJob extends SwingWorker<Void, Void> {

	/**
	 * The list to tag
	 */
	protected List<IMusicFile> listOfFilesToTag = null;

	/**
	 * The status bar to display a message at the end
	 */
	protected StatusBar statusBar = null;

	/**
	 * 
	 * @param list
	 * @param statusBar
	 */
	public TagJob(List<IMusicFile> list, StatusBar statusBar) {
		this.statusBar = statusBar;
		this.listOfFilesToTag = list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		int totalCount = this.listOfFilesToTag.size();
		int errorCount = 0;
		setProgress(0);
		for (int i = 0; i < this.listOfFilesToTag.size(); i++) {
			IMusicFile file = this.listOfFilesToTag.get(i);
			if (file.isTaggable()) {
				try {
					file.tag(false);
				} catch (Exception e) {
					errorCount++;
					e.printStackTrace();
				}
			}
			setProgress(i * 100 / totalCount);
		}
		setProgress(100);
		if (errorCount == 0) {
			this.statusBar.printMessage(totalCount + StringUtils.plural(" file", totalCount) + " tagged");
		} else {
			this.statusBar.printError(totalCount + StringUtils.plural(" file", totalCount) + " tagged with "
					+ errorCount + StringUtils.plural(" error", errorCount));

		}
		return null;
	}
}
