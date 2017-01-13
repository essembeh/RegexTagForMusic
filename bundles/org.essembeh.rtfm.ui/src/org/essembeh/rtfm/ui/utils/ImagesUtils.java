package org.essembeh.rtfm.ui.utils;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.essembeh.rtfm.model.AttributeConstants;
import org.essembeh.rtfm.model.rtfm.Node;
import org.essembeh.rtfm.model.utils.NodeUtils;
import org.essembeh.rtfm.ui.SharedImages;

public class ImagesUtils {

	private static final Map<String, String> EXTENSION_IMAGES = new HashMap<>();

	static {
		EXTENSION_IMAGES.put("mp3", SharedImages.MUSIC);
		EXTENSION_IMAGES.put("flac", SharedImages.MUSIC);
		EXTENSION_IMAGES.put("jpg", SharedImages.PICTURE);
		EXTENSION_IMAGES.put("png", SharedImages.PICTURE);
	}

	public static Image getNodeImage(Node in) {
		String image = SharedImages.PAGE_WHITE;
		if (NodeUtils.isFolder(in)) {
			image = SharedImages.FOLDER;
		} else {
			String extension = in.getAttributes().get(AttributeConstants.File.EXTENSION);
			if (extension != null && EXTENSION_IMAGES.containsKey(extension)) {
				image = EXTENSION_IMAGES.get(extension);
			}
		}
		return SharedImages.getImage(image);
	}
}
