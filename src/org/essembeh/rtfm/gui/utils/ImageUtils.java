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
package org.essembeh.rtfm.gui.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.jdesktop.swingx.graphics.GraphicsUtilities;

public class ImageUtils {

	/**
	 * The name of the folder containing the icons
	 */
	public static final String ICONS_FOLDER = "icons/";

	protected String filename;

	/**
	 * Constructor which set the image file name which the one given in
	 * parameters
	 * 
	 * @param filename
	 */
	public ImageUtils(String filename) {
		this.filename = filename;
	}

	/**
	 * Constructor which set the image file name which the one given in
	 * parameters
	 * 
	 * @param filename
	 */
	public ImageUtils(Image image) {
		this.filename = image.filename;
	}

	/**
	 * Get the buffered image of the imagefilename
	 * 
	 * @return
	 * @throws IOException
	 */
	public BufferedImage getImage() throws IOException {
		String theFilename = ICONS_FOLDER + this.filename;
		InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(theFilename);
		return ImageIO.read(stream);
	}

	/**
	 * Create the Thumbnail defined by the width and height dimension of the
	 * previous given imagefilename
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
	public Icon getIcon() throws IOException {
		return getIcon(20, 20);
	}

}
