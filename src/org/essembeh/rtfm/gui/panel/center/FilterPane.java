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
package org.essembeh.rtfm.gui.panel.center;

import java.awt.Dimension;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.library.filter.CommonFilters;
import org.essembeh.rtfm.core.library.filter.Filter;
import org.essembeh.rtfm.core.library.filter.conditions.ConditionsUtils;
import org.essembeh.rtfm.core.library.filter.conditions.TypeCondition;
import org.essembeh.rtfm.core.library.filter.conditions.VirtualPathCondition;
import org.essembeh.rtfm.gui.controller.GuiController;
import org.essembeh.rtfm.gui.listener.FilterUpdatedListener;
import org.jdesktop.swingx.HorizontalLayout;

public class FilterPane extends JPanel {

	private static final long serialVersionUID = -4784113417411996447L;

	private static Logger logger = Logger.getLogger(FilterPane.class);

	JTextField pathRegexTextField;

	JTextField filterOnAttributes;

	JComboBox typeComboBox;

	GuiController controller;

	public FilterPane(final GuiController controller) {

		this.controller = controller;

		// Regex on path
		pathRegexTextField = new JTextField();
		pathRegexTextField.setPreferredSize(new Dimension(130, pathRegexTextField.getPreferredSize().height));

		// Type
		typeComboBox = new JComboBox();
		typeComboBox.setPreferredSize(new Dimension(110, typeComboBox.getPreferredSize().height));

		// Filter on attribute
		filterOnAttributes = new JTextField();
		filterOnAttributes.setPreferredSize(new Dimension(130, filterOnAttributes.getPreferredSize().height));

		setLayout(new HorizontalLayout());
		add(new JLabel("Type:", SwingConstants.RIGHT));
		add(this.typeComboBox);
		add(new JLabel("Path contains:", SwingConstants.RIGHT));
		add(this.pathRegexTextField);
		add(new JLabel("Attributes:", SwingConstants.RIGHT));
		add(filterOnAttributes);

		// Set listeners
		FilterUpdatedListener listener = new FilterUpdatedListener(controller);
		typeComboBox.addActionListener(listener);
		filterOnAttributes.addActionListener(listener);
		pathRegexTextField.addKeyListener(listener);
	}

	public Filter getFilter() {
		Filter f = CommonFilters.noFilter();
		String type = (String) typeComboBox.getSelectedItem();
		if (type != null && type.length() > 0 && !"All".equals(type)) {
			f.addCondition(new TypeCondition(new String[] { type }));
		}
		String path = pathRegexTextField.getText();
		if (path.length() > 0) {
			String regex = ".*" + path + ".*";
			f.addCondition(new VirtualPathCondition(Pattern.compile(regex)));
		}
		String attributes = filterOnAttributes.getText();
		if (attributes.length() > 0) {
			ConditionsUtils utils = new ConditionsUtils();
			f.addConditions(utils.stringToConditions(attributes));
		}
		logger.debug("Filter: " + f);
		return f;
	}

	public void setTypeList(List<String> list) {
		this.typeComboBox.removeAllItems();
		this.typeComboBox.addItem("All");
		for (String string : list) {
			this.typeComboBox.addItem(string);
		}
		this.typeComboBox.setSelectedIndex(0);
	}
}
