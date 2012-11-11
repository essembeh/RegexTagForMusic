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
package org.essembeh.rtfm.ui.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;
import org.jdesktop.swingx.graphics.GraphicsUtilities;

public class Image {

	/**
	 * Commons images
	 */
	public final static Image SCAN_FOLDER = new Image("library-scan.png");
	public final static Image OPEN_LIBRARY = new Image("library-open.png");
	public final static Image SAVE_LIBRARY = new Image("library-save.png");
	public final static Image WORKFLOW = new Image("workflow.png");
	public final static Image ATTRIBUTES = new Image("attributes.png");
	public final static Image DIALOG_QUESTION = new Image("dialog-question.png");
	public final static Image DIALOG_INFORMATION = new Image("dialog-information.png");
	public final static Image DIALOG_WARNING = new Image("dialog-warning.png");
	public final static Image DIALOG_ERROR = new Image("dialog-error.png");
	public final static Image DIALOG_YES = new Image("dialog-yes.png");
	public final static Image DIALOG_NO = new Image("dialog-no.png");

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(Image.class);

	/**
	 * The name of the folder containing the icons
	 */
	private static final String ICONS_FOLDER = "icons/";

	/**
	 * Attributes
	 */
	protected String filename;

	/**
	 * Constructor which set the image file name which the one given in parameters
	 * 
	 * @param filename
	 */
	public Image(String filename) {
		this.filename = ICONS_FOLDER + filename;
	}

	/**
	 * Constructor which set the image file name which the one given in parameters
	 * 
	 * @param filename
	 */
	public Image(Image image) {
		this.filename = image.filename;
	}

	/**
	 * Get the buffered image of the imagefilename
	 * 
	 * @return
	 * @throws IOException
	 */
	public BufferedImage getImage() throws IOException {
		String theFilename = this.filename;
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(theFilename);
		if (stream == null) {
			logger.warn("Cannot find image: " + this.filename);
		}
		return ImageIO.read(stream);
	}

	/**
	 * Create the Thumbnail defined by the width and height dimension of the previous given imagefilename
	 * 
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public BufferedImage getThumbnail(int width, int height) throws IOException {
		return GraphicsUtilities.createThumbnail(getImage(), width, height);
	}

	/**
	 * Get the icon of the image filename according the given dimension
	 * 
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 */
	public Icon getIcon(int width, int height) throws IOException {
		return new ImageIcon(getThumbnail(width, height));
	}

	/**
	 * Get the icon of the image filename according the given dimension
	 * 
	 * @param dim
	 * @return
	 * @throws IOException
	 */
	public Icon getIcon(int dim) throws IOException {
		return new ImageIcon(getThumbnail(dim, dim));
	}

	/**
	 * Get the icon of the image filename according the given dimension
	 * 
	 * @return
	 * @throws IOException
	 */
	public Icon getCommonIcon() throws IOException {
		return getIcon(20, 20);
	}

}
