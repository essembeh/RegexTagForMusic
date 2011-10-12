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
package org.essembeh.rtfm.gui.panel;

import javax.swing.JCheckBox;

import org.essembeh.rtfm.core.conf.RTFMProperties;
import org.essembeh.rtfm.core.exception.ConfigurationException;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.listener.UpdateOptionListener;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.VerticalLayout;

public class PropertiesCommands extends JXTaskPane {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5429472164954626078L;

	/**
	 * Constructor
	 * 
	 * @param controller
	 * @throws ConfigurationException
	 */
	public PropertiesCommands(RTFMController controller) throws ConfigurationException {
		super("Options");
		setLayout(new VerticalLayout());
		add(createCheckBoxOption(controller, "string.gui.options.scan_hidden_files", "scan.hidden.files"));
		add(createCheckBoxOption(controller, "string.gui.options.replace_underscore_by_space",
				"tag.regexfield.replace_underscore_by_space"));
		add(createCheckBoxOption(controller, "string.gui.options.capitalize_first_letters",
				"tag.regexfield.capitalize_first_letters"));
		setCollapsed(true);
	}

	/**
	 * 
	 * @param controller
	 * @param propertyName
	 * @param uiStringKey
	 * @return
	 * @throws ConfigurationException
	 */
	protected JCheckBox createCheckBoxOption(RTFMController controller, String uiStringKey, String propertyName)
			throws ConfigurationException {
		// Create the checkbox and set the UI String
		JCheckBox checkBox = new JCheckBox(RTFMProperties.getMandatoryProperty(uiStringKey));
		// Set selection
		checkBox.setSelected("true".equalsIgnoreCase(RTFMProperties.getMandatoryProperty(propertyName)));
		// Add listener
		checkBox.addActionListener(new UpdateOptionListener(controller, propertyName));
		return checkBox;

	}
}
