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
package org.essembeh.rtfm.gui.action;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.conf.RTFMProperties;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.utils.Image;
import org.essembeh.rtfm.gui.utils.ImageUtils;

public abstract class GenericAction extends AbstractAction {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -4801896580741417417L;

	/**
	 * The controller
	 */
	protected RTFMController controller = null;

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(GenericAction.class);

	/**
	 * Constructor
	 * 
	 * @param controller
	 * @param stringKey
	 * @param image
	 * @throws ConfigurationException
	 */
	public GenericAction(RTFMController controller, String stringKey, Image image) throws ConfigurationException {
		this.controller = controller;
		setText(stringKey);
		setIcon(image);
	}

	/**
	 * Sets the text
	 * 
	 * @param stringKey
	 * @throws ConfigurationException
	 */
	protected void setText(String stringKey) throws ConfigurationException {
		putValue(NAME, RTFMProperties.getMandatoryProperty(stringKey));
	}

	/**
	 * Sets the icon of the action
	 * 
	 * @param image
	 * @return
	 */
	protected void setIcon(Image image) {

		try {
			putValue(SMALL_ICON, new ImageUtils(image).getIcon());
		} catch (Exception e) {
			logger.error("Error loading image: " + image);
			logger.error(e.toString());
		}
	}
}
