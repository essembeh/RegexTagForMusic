package org.essembeh.rtfm.ui.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

public class ImageUtils {

	/**
	 * Commons images
	 */
	public final static ImageUtils SCAN_FOLDER = new ImageUtils("library-scan.png");
	public final static ImageUtils OPEN_LIBRARY = new ImageUtils("library-open.png");
	public final static ImageUtils SAVE_LIBRARY = new ImageUtils("library-save.png");
	public final static ImageUtils WORKFLOW = new ImageUtils("workflow.png");
	public final static ImageUtils ATTRIBUTES = new ImageUtils("attributes.png");
	public final static ImageUtils DIALOG_QUESTION = new ImageUtils("dialog-question.png");
	public final static ImageUtils DIALOG_INFORMATION = new ImageUtils("dialog-information.png");
	public final static ImageUtils DIALOG_WARNING = new ImageUtils("dialog-warning.png");
	public final static ImageUtils DIALOG_ERROR = new ImageUtils("dialog-error.png");
	public final static ImageUtils DIALOG_YES = new ImageUtils("dialog-yes.png");
	public final static ImageUtils DIALOG_NO = new ImageUtils("dialog-no.png");

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(ImageUtils.class);

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
	public ImageUtils(String filename) {
		this.filename = ICONS_FOLDER + filename;
	}

	/**
	 * Constructor which set the image file name which the one given in parameters
	 * 
	 * @param filename
	 */
	public ImageUtils(ImageUtils image) {
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
	public Image getThumbnail(int width, int height) throws IOException {
		return getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
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
