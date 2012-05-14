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
package org.essembeh.rtfm.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.ui.utils.Image;
import org.essembeh.rtfm.ui.utils.ImageUtils;

public class DefaultRtfmAction extends AbstractAction {

	private static final long serialVersionUID = -4801896580741417417L;
	private static final Logger logger = Logger.getLogger(DefaultRtfmAction.class);
	private final ICallback callback;

	/**
	 * 
	 * @param text
	 * @param image
	 */
	public DefaultRtfmAction(String text, Image image) {
		this(text, image, null);
	}

	/**
	 * 
	 * @param text
	 * @param image
	 * @param callback
	 */
	public DefaultRtfmAction(String text, Image image, ICallback callback) {
		setText(text);
		setIcon(image);
		this.callback = callback;
	}

	/**
	 * 
	 * @param text
	 */
	protected void setText(String text) {
		if (text != null) {
			putValue(NAME, text);
		}
	}

	/**
	 * 
	 * @param image
	 */
	protected void setIcon(Image image) {
		if (image != null) {
			try {
				putValue(SMALL_ICON, new ImageUtils(image).getIcon());
			} catch (Exception e) {
				logger.error("Error loading image: " + image);
				logger.error(e.toString());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	final public void actionPerformed(ActionEvent e) {
		if (callback != null) {
			callback.execute();
		}
	}

	/**
	 * 
	 * @author smassot
	 * 
	 */
	public interface ICallback {
		/**
		 * 
		 */
		void execute();
	}
}
