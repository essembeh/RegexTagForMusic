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
