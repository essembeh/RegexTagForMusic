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
