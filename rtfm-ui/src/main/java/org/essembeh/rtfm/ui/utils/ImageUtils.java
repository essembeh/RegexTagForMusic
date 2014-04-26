package org.essembeh.rtfm.ui.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.essembeh.rtfm.fs.util.FileTools;

public enum ImageUtils {
	SCAN_FOLDER("library-scan.png"), OPEN_LIBRARY("library-open.png"), SAVE_LIBRARY("library-save.png"), WORKFLOW(
			"workflow.png"), ATTRIBUTES("attributes.png"), DIALOG_QUESTION("dialog-question.png"), DIALOG_INFORMATION(
			"dialog-information.png"), DIALOG_WARNING("dialog-warning.png"), DIALOG_ERROR("dialog-error.png"), DIALOG_YES(
			"dialog-yes.png"), DIALOG_NO("dialog-no.png");

	private static final String ICONS_FOLDER = "icons/";
	private BufferedImage image;
	private final String filename;

	ImageUtils(String filename) {
		this.filename = filename;
		this.image = null;
	}

	public BufferedImage getImage() throws IOException {
		if (image == null) {
			image = ImageIO.read(FileTools.getResourceAsStream(ICONS_FOLDER + filename));
		}
		return image;
	}

	public Image getThumbnail(int width, int height) throws IOException {
		return getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
	}

	public Icon getIcon(int width, int height) throws IOException {
		return new ImageIcon(getThumbnail(width, height));
	}

	public Icon getIcon(int dim) throws IOException {
		return new ImageIcon(getThumbnail(dim, dim));
	}

	public Icon getCommonIcon() throws IOException {
		return getIcon(20, 20);
	}

}
