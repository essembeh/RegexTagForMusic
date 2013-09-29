package org.essembeh.rtfm.ui.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.ui.utils.Image;

public class DefaultRtfmAction extends AbstractAction {

	private static final long serialVersionUID = -4801896580741417417L;
	private static final Logger logger = Logger.getLogger(DefaultRtfmAction.class);
	private final Runnable runnable;

	public DefaultRtfmAction(String text, Image image) {
		this(text, image, null);
	}

	public DefaultRtfmAction(String text, Image image, Runnable runnable) {
		setText(text);
		setIcon(image);
		this.runnable = runnable;
	}

	protected void setText(String text) {
		if (text != null) {
			putValue(NAME, text);
		}
	}

	protected void setIcon(Image image) {
		if (image != null) {
			try {
				putValue(SMALL_ICON, image.getCommonIcon());
			} catch (Exception e) {
				logger.error("Error loading image: " + image);
				logger.error(e.toString());
			}
		}
	}

	@Override
	final public void actionPerformed(ActionEvent e) {
		runnable.run();
	}
}
