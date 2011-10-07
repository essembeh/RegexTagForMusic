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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;
import org.essembeh.rtfm.core.Filter;
import org.essembeh.rtfm.core.Filter.Status;
import org.essembeh.rtfm.gui.controller.RTFMController;
import org.essembeh.rtfm.gui.listener.UpdateFilter;

public class FilterPane extends JPanel {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -4784113417411996447L;

	/**
	 * String REGEX for ANYTHING
	 */
	private static final String ANYTHING = ".*";

	/**
	 * Logger
	 */
	private static Logger logger = Logger.getLogger(FilterPane.class);

	/**
	 * Used to translate filter for UI.
	 */
	Map<Status, String> taggedStrings = new HashMap<Filter.Status, String>();
	Map<Status, String> taggableStrings = new HashMap<Filter.Status, String>();

	/**
	 * UI Items
	 */
	JComboBox taggableComboBox;
	JComboBox taggedComboBox;
	JTextField pathRegexTextField;
	JComboBox typeComboBox;

	/**
	 * The controller
	 */
	RTFMController controller;

	/**
	 * Constructor
	 * 
	 * @param controller
	 *            the controller
	 * @param filter
	 *            the filter to show
	 */
	public FilterPane(final RTFMController controller) {

		this.controller = controller;

		// Strings to translate a filter for UI
		this.taggableStrings.put(Status.ENABLE, "Taggable");
		this.taggableStrings.put(Status.INVERSE, "Not taggable");
		this.taggableStrings.put(Status.NO_FILTER, "All");
		this.taggedStrings.put(Status.ENABLE, "Tagged");
		this.taggedStrings.put(Status.INVERSE, "Not tagged");
		this.taggedStrings.put(Status.NO_FILTER, "All");

		// Create UI
		this.taggableComboBox = new JComboBox();
		this.taggableComboBox.setEditable(false);
		this.taggableComboBox.addItem(this.taggableStrings.get(Status.NO_FILTER));
		this.taggableComboBox.addItem(this.taggableStrings.get(Status.ENABLE));
		this.taggableComboBox.addItem(this.taggableStrings.get(Status.INVERSE));
		this.taggedComboBox = new JComboBox();
		this.taggedComboBox.setEditable(false);
		this.taggedComboBox.addItem(this.taggedStrings.get(Status.NO_FILTER));
		this.taggedComboBox.addItem(this.taggedStrings.get(Status.ENABLE));
		this.taggedComboBox.addItem(this.taggedStrings.get(Status.INVERSE));
		this.pathRegexTextField = new JTextField();
		this.typeComboBox = new JComboBox();

		setLayout(new GridLayout(2, 4));
		add(new JLabel("Taggable ", SwingConstants.RIGHT));
		add(this.taggableComboBox);
		add(new JLabel("Tagged ", SwingConstants.RIGHT));
		add(this.taggedComboBox);
		add(new JLabel("Path contains ", SwingConstants.RIGHT));
		add(this.pathRegexTextField);
		add(new JLabel("Type ", SwingConstants.RIGHT));
		add(this.typeComboBox);

		// Set listeners
		this.taggableComboBox.addActionListener(new UpdateFilter(controller));
		this.taggedComboBox.addActionListener(new UpdateFilter(controller));
		this.typeComboBox.addActionListener(new UpdateFilter(controller));
		this.pathRegexTextField.addKeyListener(new UpdateFilter(controller));
		// Anonymous listener because only does nothing interesting
		this.taggedComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (FilterPane.this.taggedComboBox.getSelectedIndex() > 0) {
					FilterPane.this.taggableComboBox.setSelectedIndex(1);
				}
			}
		});
	}

	/**
	 * Returns the filtering options set in the panel
	 * 
	 * @return the filter
	 */
	public Filter getFilter() {
		String pathPattern = ANYTHING;
		if (this.pathRegexTextField.getText().length() > 0) {
			pathPattern = ANYTHING + this.pathRegexTextField.getText() + ANYTHING;
		}
		String typePattern = ANYTHING;
		if (this.typeComboBox.getSelectedIndex() > 0) {
			typePattern = this.typeComboBox.getSelectedItem().toString();
		}
		Filter f = new Filter(getStatusFromCombobox(this.taggableComboBox, this.taggableStrings),
				getStatusFromCombobox(this.taggedComboBox, this.taggedStrings), Pattern.compile(typePattern),
				Pattern.compile(pathPattern));

		logger.debug("Filter: " + f);
		return f;
	}

	/**
	 * Used to translate filter for UI
	 * 
	 * @param combo
	 * @param stringValues
	 * @return
	 */
	protected Status getStatusFromCombobox(JComboBox combo, Map<Status, String> stringValues) {
		Status status = Status.NO_FILTER;
		String comboValue = combo.getSelectedItem().toString();
		for (Status key : stringValues.keySet()) {
			if (stringValues.get(key).equals(comboValue)) {
				status = key;
				break;
			}
		}
		return status;
	}

	/**
	 * 
	 * @param list
	 */
	public void setTypeList(List<String> list) {
		this.typeComboBox.removeAllItems();
		this.typeComboBox.addItem("All");
		for (String string : list) {
			this.typeComboBox.addItem(string);
		}
		this.typeComboBox.setSelectedIndex(0);
	}
}
