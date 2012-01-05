package org.essembeh.rtfm.gui.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Translator {

	static ResourceBundle resourceBundle = null;

	public enum StringId {
		HELLO, inspectSelection, actionReadDatabase, messageSelectDatabase, actionScanFolder, messageSelectFolder, actionWriteDatabase, statusFolderScanned, statusDatabaseRead, statusDatabaseSaved, messageJobAlreadyRunning, buttonClose, titleInspect, columnTagged, columnType, columnPath, tabAll, tabNew, tabUnknown, titleActionsOnAll, titleActionsOnSelection
	}

	public static String get(StringId key) {
		if (resourceBundle == null) {
			resourceBundle = ResourceBundle.getBundle("gui");
		}
		String message = null;
		try {
			message = resourceBundle.getString(key.name());
		} catch (MissingResourceException e) {
			message = key.name();
		} catch (NullPointerException e) {
			message = key.name();
		}
		return message;
	}

}
