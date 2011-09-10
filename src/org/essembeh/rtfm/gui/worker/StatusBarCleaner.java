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

import javax.swing.SwingWorker;

import org.essembeh.rtfm.gui.panel.StatusBar;

public class StatusBarCleaner extends SwingWorker<Void, Void> {

	/**
	 * Time out in seconds
	 */
	protected int timeoutInSeconds = 0;

	/**
	 * The status bar to clean
	 */
	protected StatusBar statusBar = null;

	/**
	 * Constructor
	 * 
	 * @param statusBar
	 * @param timeoutInSeconds
	 */
	public StatusBarCleaner(StatusBar statusBar, int timeoutInSeconds) {
		this.timeoutInSeconds = timeoutInSeconds;
		this.statusBar = statusBar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		if (this.timeoutInSeconds > 0) {
			Thread.sleep(this.timeoutInSeconds * 1000);
			this.statusBar.clear();
		}
		return null;
	}
}
