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

import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class JLabelDelayedMessage extends SwingWorker<String, Object> {

	/**
	 * Time out in seconds
	 */
	protected int timeoutInSeconds = 0;

	/**
	 * The label to set text
	 */
	protected JLabel label = null;

	/**
	 * The message to set after timeout
	 */
	protected String message = null;

	/**
	 * Constructor
	 * 
	 * @param label
	 * @param timeoutInSeconds
	 * @param messageToSet
	 */
	public JLabelDelayedMessage(JLabel label, int timeoutInSeconds,
			String messageToSet) {
		this.timeoutInSeconds = timeoutInSeconds;
		this.label = label;
		this.message = messageToSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected String doInBackground() throws Exception {
		String oldValue = null;
		if (this.label != null && timeoutInSeconds > 0) {
			Thread.sleep(this.timeoutInSeconds * 1000);
			oldValue = this.label.getText();
			this.label.setText(this.message);
		}
		return oldValue;
	}
}
